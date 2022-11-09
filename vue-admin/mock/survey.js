module.exports = [
  {
    url: "/vue-admin-template/survey/getQuestionnaires\.*",
    type: "get",
    response: config => {
      const { token } = config.query
      const info = users[token]

      // mock error
      if (!info) {
        return {
          code: 50008,
          message: "Login failed, unable to get user details."
        }
      }

      return {
        code: 20000,
        data: info
      }
    }
  },
]
