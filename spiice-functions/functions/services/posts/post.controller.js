const { db } = require("../../libs/firebase.config");
const { isClientError, isInternalError } = require("../errors/error.service");
const { isEmpty } = require("../../utils/is-validation");
const { extractUserRefDoc } = require("./post.utils");
const postCollection = "posts";
module.exports = postController = {
  postCollection,
  index: async (req, res) => {
    try {
      const result = await db.collection(postCollection).get();
      const userKey = "user";
      const data = await extractUserRefDoc(result, userKey);
      return res.send({ data, status: 200 });
    } catch (err) {
      return isInternalError(res, err);
    }
  },

  create: async (req, res) => {
    try {
      const { header, description, amount, skills } = req.body;
      const errors = {};
      isEmpty(header) === true ? (errors.header = "header is empty") : null;
      isEmpty(description) === true
        ? (errors.description = "description is empty")
        : null;
      isEmpty(amount) === true ? (errors.amount = "amount is empty") : null;

      if (Object.keys(errors).length > 0) return isClientError(res, errors);

      const { email } = req.user;
      const newPost = {
        header,
        description,
        amount,
        skills: skills || [],
        user: { ref: db.doc(`users/${email}`), email },
        proposition:0,
        createdAt: new Date().toISOString()
      };

      const result = await db.collection(postCollection).add(newPost);
      const uid = result.id;
      const data = await (await result.get()).data();
      delete data.user.ref;
      data.uid = uid;
      return res.status(201).send({ data, status: 201 });
    } catch (err) {
      return isInternalError(req, err);
    }
  },
  getUserPost: async (req, res) => {
    try {
      const { email } = req.user;
      const result = await db
        .collection(postCollection)
        .where("user.email", "==", email)
        .get();
      const data = [];
      result.forEach(doc => {
        const dc = doc.data();
        delete dc.user;
        data.push({ ...dc });
      });

      return res.send({ data, status: 200 });
    } catch (err) {
      return isInternalError(res, err);
    }
  }
};
