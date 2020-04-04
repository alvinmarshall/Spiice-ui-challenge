const { db, firebase, fbConfig } = require("../../libs/firebase.config");
const axios = require("axios");
const { isEmpty, isNotEmail } = require("../../utils/is-validation");
const { isClientError, isInternalError } = require("../errors/error.service");
const { reviewCollection } = require("../reviews/reviews.controller");
const { extractUserRefDoc } = require("../posts/post.utils");
module.exports = userController = {
  sigin: async (req, res) => {
    try {
      const { email, password } = req.body;
      const errors = {};
      isNotEmail(email) === true ? (errors.email = "email invalid") : null;
      isEmpty(email) === true ? (errors.password = "password empty") : null;

      if (Object.keys(errors).length > 0) return isClientError(res, errors);

      const result = await firebase
        .auth()
        .signInWithEmailAndPassword(email, password);
      const token = await result.user.getIdToken(true);
      const refreshToken = result.user.refreshToken;
      const userDoc = await db.doc(`users/${email}`).get();
      const user = userDoc.data();

      return res.send({
        message: "login successful",
        data: user,
        token,
        refreshToken,
        status: 200,
      });
    } catch (err) {
      return isInternalError(res, err);
    }
  },
  signup: async (req, res) => {
    try {
      const { email, password, name } = req.body;
      const errors = {};

      isNotEmail(email) === true ? (errors.email = "email invalid") : null;
      isEmpty(name) === true ? (errors.name = "name empty") : null;
      isEmpty(password) === true ? (errors.password = "password empty") : null;

      if (Object.keys(errors).length > 0) return isClientError(res, errors);
      const docs = await db.doc(`/users/${email}`).get();

      if (docs.exists) {
        errors.email = "email already in use";
        return isClientError(res, errors);
      }

      const result = await firebase
        .auth()
        .createUserWithEmailAndPassword(email, password);
      const uid = result.user.uid;
      const token = await result.user.getIdToken();
      const refreshToken = result.user.refreshToken;
      const noImg = "no_avatar.png";
      const avatarUrl = `https://firebasestorage.googleapis.com/v0/b/${fbConfig.storageBucket}/o/${noImg}?alt=media`;
      const newUser = {
        userId: uid,
        name,
        email,
        avatarUrl,
        createdAt: new Date().toISOString(),
      };

      const _ = await db.doc(`/users/${email}`).set(newUser);

      const profileRef = db.doc(`/profile/${email}`);
      const profile = {
        user: db.doc(`users/${email}`),
        jobTitle: "Hey my jobTitle",
        description: "Hey there I'm using spiice",
        portfolio: [
          { screenShotUrl: "https://i.picsum.photos/id/356/200/300.jpg" },
          { screenShotUrl: "https://i.picsum.photos/id/156/200/300.jpg" },
        ],
      };
      const _prf = await profileRef.set(profile);

      return res.status(201).send({
        message: `user ${uid} registered`,
        token,
        refreshToken,
        status: 201,
        data: newUser,
      });
    } catch (err) {
      return isInternalError(res, err);
    }
  },

  createProfile: async (req, res) => {
    try {
      const { jobTitle, description, portfolio } = req.body;
      const errors = {};
      isEmpty(jobTitle) === true ? (errors.jobTitle = "job title empty") : null;
      isEmpty(description) === true
        ? (errors.jobTitle = "description empty")
        : null;
      const { email } = req.user;
      const profile = {
        user: db.doc(`users/${email}`),
        jobTitle,
        description,
        portfolio: portfolio || [],
      };
      const profileRef = db.doc(`/profile/${email}`);
      const _ = await profileRef.set(profile);
      const result = await profileRef.get();
      const data = result.data();
      delete data.user;
      data.uid = profileRef.id;
      return res.status(201).send({ data, status: 201 });
    } catch (err) {
      return isInternalError(res, err);
    }
  },
  getProfile: async (req, res) => {
    try {
      const { email } = req.user;
      const profileRef = await db.doc(`/profile/${email}`).get();
      const profile = profileRef.data();
      const userRef = profile.user;

      const resUser = await userRef.get();
      const userData = resUser.data();
      profile.user = userData;

      const rsReview = await db
        .collection(reviewCollection)
        .where("userEmail", "==", email)
        .get();
      const senderKey = "sender";
      const reviews = await extractUserRefDoc(rsReview, senderKey);
      const data = {
        ...profile,
        reviews,
      };

      return res.send({ data, status: 200 });
    } catch (err) {
      return isInternalError(res, err);
    }
  },
  refreshAccessToken: async (req, res) => {
    try {
      const { refresh_token } = req.body;
      const errors = {};
      const url = `https://securetoken.googleapis.com/v1/token?key=${fbConfig.apiKey}`;

      isEmpty(refresh_token) === true
        ? (errors.refresh_token = "missing refresh_token")
        : null;
      if (Object.keys(errors).length > 0) return isClientError(res, errors);

      const resp = await axios.post(url, {
        grant_type: "refresh_token",
        refresh_token,
      });
      const data = resp.data;
      return res.send({ data, status: 200 });
    } catch (err) {
      const error = err.response.data || null;
      if (error) return isClientError(res, error);
      return isInternalError(res);
    }
  },
};
