const express = require("express");
const router = express.Router();
const tokenAuth = require("../../utils/token-utils");
const controller = require("./post.controller");

router.get("/", controller.index);
router.get("/user", tokenAuth, controller.getUserPost);
router.post("/", tokenAuth, tokenAuth, controller.create);

module.exports = router;
