export default {
  name: "MenuBar",

  methods: {
    logoutUser: function(){
        // logout user by removing localstorage information
        localStorage.removeItem("Username")
        localStorage.removeItem("Email")
        localStorage.removeItem("Password")
        localStorage.removeItem("isLibrarian")
        localStorage.removeItem("Address")
        location.reload()
    }
  },
  mounted: function currentPage() {

    let username = localStorage.getItem("Username")
    let isLibrarian = false

    if (username == null){
        // nobody is logged in
        //only display the home page

        document.querySelector(".home").hidden = false
    }

    else {
        isLibrarian = localStorage.getItem("isLibrarian")

        document.getElementById("login-btn").hidden = true
        document.querySelector(".home").hidden = false
        document.querySelector(".room-booking").hidden = false
        document.querySelector(".account").hidden = false
        document.querySelector(".browse").hidden = false
        document.getElementById("logout-btn").hidden = false

        if (isLibrarian){
            document.querySelector(".tools").hidden = false

            // if (isHeadLibrarian){
            //     document.querySelector(".management").hidden = false
            // }
        }
    }

    const url = window.location.href;
    const host = window.location.host;
    const path = url.split(host)[1];

    if (path == "/#/") {
      document.querySelector(".home").className += " active";
    }

    if (path == "/#/rooms") {
      document.querySelector(".room-booking").className += " active";
    }

    if (path == "/#/tools") {
      document.querySelector(".tools").className += " active";
    }

    if (path == "/#/management") {
      document.querySelector(".management").className += " active";
    }

    if (path == "/#/account") {
      document.querySelector(".account").className += " active";
    }

    if (path == "/#/browse") {
        document.querySelector(".browse").className += " active";
      }
  },
};