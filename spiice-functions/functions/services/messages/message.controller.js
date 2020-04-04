const { db } = require("../../libs/firebase.config");
const messageCollection = "messages";
const { isInternalError, isClientError } = require("../errors/error.service");
const { isEmpty } = require("../../utils/is-validation");
const { postCollection } = require("../posts/post.controller");
const { extractUserRefDoc } = require("../posts/post.utils");

module.exports = messageController = {
  messageCollection,
  index: async (req, res) => {
    try {
      const result = await db.collection(messageCollection).get();
      const data = [];
      result.forEach(doc => data.push({ ...doc.data() }));
      return res.send({ data, status: 200 });
    } catch (err) {
      return isInternalError(req, err);
    }
  },
  create: async (req, res) => {
    try {
      const { receiverEmail, postId, content } = req.body;
      const errors = {};
      isEmpty(postId) === true ? (errors.postId = "postId is empty") : null;
      isEmpty(content) === true ? (errors.content = "content is empty") : null;

      if (Object.keys(errors).length > 0) return isClientError(res, errors);

      const { email } = req.user;
      const newMessage = {
        receiver: receiverEmail,
        sender: { email, ref: db.doc(`users/${email}`) },
        content,
        post: { ref: db.collection(postCollection).doc(postId), postId },
        createdAt: new Date().toISOString()
      };

      const postRef = db.collection(postCollection).doc(postId);
      const postDoc = await postRef.get();
      let proposition = postDoc.data().proposition;

      const result = await await db
        .collection(messageCollection)
        .add(newMessage);
      const id = result.id;
      const data = (await result.get()).data();
      data.uid = id;
      proposition++;

      const _ = await postRef.update({ proposition });
      delete data.sender;
      return res.status(201).send({ data, status: 201 });
    } catch (err) {
      return isInternalError(req, err);
    }
  },
  getSentMessages: async (req, res) => {
    try {
      const { email } = req.user;
      const result = await db
        .collection(messageCollection)
        .where("sender.email", "==", email)
        .get();
      const data = await getPostRefAsync(result);
      return res.send({ data, status: 200 });
    } catch (err) {
      return isInternalError(res, err);
    }
  },
  getReceiverMessages: async (req, res) => {
    try {
      const { email } = req.user;
      const result = await db
        .collection(messageCollection)
        .where("receiver", "==", email)
        .get();
      const senderKey = "sender";
      const data = await extractUserRefDoc(result, senderKey);
      data.forEach(d => delete d.post.ref);
      return res.send({ data, status: 200 });
    } catch (err) {
      return isInternalError(res, err);
    }
  }
};

const getPostRefAsync = result => {
  const aPromise = item => {
    return Promise.resolve(item);
  };
  const getPost = async docs => {
    const id = docs.id;
    const doc = docs.data();
    delete doc.sender;
    const postRef = doc.post.ref;
    const _ = await postRef.get();
    doc.post = _.data();

    const post = doc.post;
    const userRef = post.user.ref;
    const _user = await userRef.get();
    doc.post.user = _user.data();
    doc.id = id;

    return aPromise(doc);
  };
  const getData = result => {
    return Promise.all(result.docs.map(docs => getPost(docs)));
  };
  return getData(result);
};
