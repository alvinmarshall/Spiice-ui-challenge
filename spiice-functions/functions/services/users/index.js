const express = require("express");
const router = express.Router();
const controller = require("./users.controller");
const tokenAuth = require("../../utils/token-utils");

router.post("/signup", controller.signup);
router.post("/signin", controller.sigin);
router.post("/profile", tokenAuth, controller.createProfile);
router.get("/profile", tokenAuth ,controller.getProfile);
router.post("/refresh_token", controller.refreshAccessToken);

module.exports = router;
