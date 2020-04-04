const { admin, db } = require("../libs/firebase.config");
const { notAuthorizeError } = require("../services/errors/error.service");
module.exports = tokenAuth = async (req, res, next) => {
  try {
    let idToken;
    if (
      req.headers.authorization &&
      req.headers.authorization.startsWith("Bearer ")
    ) {
      idToken = req.headers.authorization.split("Bearer ")[1];
      const decodedToken = await admin.auth().verifyIdToken(idToken);
      req.user = decodedToken;
      const data = await db
        .collection("users")
        .where("userId", "==", req.user.uid)
        .limit(1)
        .get();

      req.user.avatarUrl = data.docs[0].data().avatarUrl;
      req.user.name = data.docs[0].data().name;
      return next();
    }
    return notAuthorizeError(res);
  } catch (err) {
    return notAuthorizeError(res, err);
  }
};
