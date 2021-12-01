export default {
  name: "MenuBar",

  methods: {
    logoutUser: function(){
        localStorage.clear()
        document.getElementById("logout-btn").hidden = true
        location.reload()
    }
  },
  mounted: function currentPage() {

    let username = localStorage.getItem("Username")
    
    if (username == null){
      // nobody is logged in
      //only display the home page
      
      document.querySelector(".home").hidden = false
    }
    
    else {
      const isLibrarian = localStorage.getItem("isLibrarian")
      
      document.getElementById("login-btn").hidden = true
      document.querySelector(".home").hidden = false
      document.querySelector(".room-booking").hidden = false
      document.querySelector(".account").hidden = false
      document.querySelector(".browse").hidden = false
      document.getElementById("logout-btn").hidden = false
      document.getElementById("reservations").hidden = false;
      
        if (isLibrarian == "true"){

          document.querySelector(".room-booking").hidden = true
          document.querySelector(".tools").hidden = false
          document.getElementById("reservations").hidden = true;

            if (username == "headLibrarian"){
               document.querySelector(".management").hidden = false
            }
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