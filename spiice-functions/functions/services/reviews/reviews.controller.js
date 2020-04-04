const { db } = require("../../libs/firebase.config");
const { isEmpty, isNotEmail } = require("../../utils/is-validation");
const { isClientError, isInternalError } = require("../errors/error.service");
const reviewCollection = "reviews";
module.exports = reviewController = {
  reviewCollection,
  index: async (req, res) => {
    try {
      const result = await db.collection(reviewCollection).get();
      const data = [];
      result.forEach(doc => data.push({ ...doc.data() }));
      return res.send({ data, status: 200 });
    } catch (err) {
      return isInternalError(res, err);
    }
  },
  create: async (req, res) => {
    try {
      const { rating, userEmail, content } = req.body;
      const errors = {};
      isNotEmail(userEmail) === true
        ? (errors.userEmail = "userEmail invalid")
        : null;
      isEmpty(content) === true ? (errors.content = "content is empty") : null;
      isEmpty(rating) === true ? (errors.rating = "set a rating") : null;

      if (Object.keys(errors).length > 0) return isClientError(res, errors);

      const { email } = req.user;
      const newReview = {
        userEmail,
        rating,
        content,
        sender: { ref: db.doc(`users/${email}`), email },
        createdAt: new Date().toISOString()
      };

      const result = await db.collection(reviewCollection).add(newReview);
      const uid = result.id;
      const data = await (await result.get()).data();
      data.uid = uid;
      delete data.sender;

      return res.status(201).send({ data, status: 201 });
    } catch (err) {
      return isInternalError(res, err);
    }
  },
  getUserReview: async (req, res) => {}
};
