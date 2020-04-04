const admin = require("firebase-admin");
const firebase = require("firebase");
const serviceAccount = require("../spiice.json");
const fbConfig = require("../fbConfig");

admin.initializeApp({
  credential: admin.credential.cert(serviceAccount),
  databaseURL: "https://spiice.firebaseio.com"
});

firebase.initializeApp(fbConfig);

const db = admin.firestore();

module.exports = { admin, db, firebase,fbConfig };
