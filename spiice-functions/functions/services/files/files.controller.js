const Busboy = require("busboy");
const fs = require("fs");
const os = require("os");
const path = require("path");
const { admin, fbConfig, db } = require("../../libs/firebase.config");
const { isInternalError } = require("../errors/error.service");
module.exports = filesController = {
  uploadAvatar: async (req, res) => {
    try {
      const busboy = new Busboy({ headers: req.headers });
      let imageName;
      let imageToUpload = {};

      busboy.on("file", (fieldname, file, filename, encoding, mimetype) => {
        //img.something.jpg/png/...
        const lastItem = filename.split(".").length - 1;
        const extension = filename.split(".")[lastItem];
        imageName = `IMG-${new Date().getTime()}.${extension}`;
        const filePath = path.join(os.tmpdir(), imageName);
        imageToUpload = { filePath, mimetype };
        file.pipe(fs.createWriteStream(filePath));
      });

      busboy.on("finish", async () => {
        try {
          const _ = await admin
            .storage()
            .bucket()
            .upload(imageToUpload.filePath, {
              resumable: false,
              metadata: {
                metadata: {
                  contentType: imageToUpload.mimetype
                }
              }
            });
          const avatarUrl = `https://firebasestorage.googleapis.com/v0/b/${fbConfig.storageBucket}/o/${imageName}?alt=media`;
          const result = await db
            .doc(`/users/${req.user.email}`)
            .update({ avatarUrl });
          return res.send({ message: "file upload successful", status: 200 });
        } catch (err) {
          return isInternalError(res, err);
        }
      });

      busboy.end(req.rawBody);
    } catch (err) {
      return isInternalError(res, err);
    }
  }
};
