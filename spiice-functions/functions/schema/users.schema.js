module.exports = usersSchema = {
  name: "first last name",
  email: "email@me.com",
  password: "password",
  avatarUrl: "image location url",
  userId: "uid",
  createdAt: "2020-03-31T00:40:16.131Z"
};

module.exports = profileSchema = {
  jobTitle: "job title",
  description: "description",
  portfolio: [{ screenShotUrl: "link 1" }, { screenShotUrl: "link 2" }],
  user: 'doc/users',
  reviews:[{}]
};
