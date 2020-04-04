const postRoute = require("./services/posts");
const userRoute = require("./services/users");
const reviewRoute = require("./services/reviews");
const fileRoute = require("./services/files");
const messageRoute = require("./services/messages");

module.exports = routes = app => {
  app.use("/posts", postRoute);
  app.use("/users", userRoute);
  app.use("/reviews", reviewRoute);
  app.use("/messages", messageRoute);
  app.use("/files", fileRoute);
};
