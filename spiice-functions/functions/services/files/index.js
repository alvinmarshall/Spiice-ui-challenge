const express = require("express");
const router = express.Router();
const controller = require("./files.controller");
const tokenAuth = require("../../utils/token-utils");
router.post("/upload/user/img", tokenAuth, controller.uploadAvatar);
module.exports = router;
