const { db } = require("../../libs/firebase.config");
const screamCollection = "screams";
const { isInternalError, isClientError } = require("../errors/error.service");
const { isEmpty } = require("../../utils/is-validation");
module.exports = screamController = {
  index: async (req, res) => {
    try {
      const result = await db.collection(screamCollection).get();
      const data = [];
      result.forEach(doc => data.push({ ...doc.data() }));
      return res.send({ data, status: 200 });
    } catch (err) {
      return isInternalError(res, err);
    }
  },
  create: async (req, res) => {
    try {
      const errors = {};
      const { body } = req.body;
      const handle = req.user.handle;

      isEmpty(body) === true ? (errors.body = "body empty") : null;

      if (Object.keys(errors).length > 0) return isClientError(res, err);

      const newData = {
        body,
        handle,
        createdAt: new Date().toISOString()
      };

      const result = await (
        await db.collection(screamCollection).add(newData)
      ).get();

      const data = result.data();
      return res.status(201).send({ data });
    } catch (err) {
      return isInternalError(res, err);
    }
  }
};
