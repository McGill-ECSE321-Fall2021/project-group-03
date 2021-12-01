import axios from "axios";
import MenuBar from "../components/MenuBar.vue";

var config = require("../../config");

var frontendUrl = "http://" + config.dev.host + ":" + config.dev.port;
var backendUrl =
  "http://" + config.dev.backendHost + ":" + config.dev.backendPort;

var AXIOS = axios.create({
  baseURL: backendUrl,
  headers: { "Access-Control-Allow-Origin": frontendUrl }
});

function TitleDto(name, description, genre, isAvailable, titleType) {
  this.name = name;
  this.description = description;
  this.genre = genre;
  this.isAvailable = isAvailable;
  this.titleType = titleType;
}

export default {
  mounted: function loadStaffSchedule(){
    const librarianUsername = localStorage.getItem("Username")
    const url = "/staffSchedules/get/" + librarianUsername

    // make the get request
    AXIOS.get(url, {}, {})
    .then(response => {
      this.staffSchedule = response.data
    })
    .catch(e => {

    });

  },

  data(){
    return {
        staffSchedule: [],
        response: []
    }
  },

  methods: {
    createTitle() {
      let titleName = document.getElementById("title-name-create").value;
      let description = document.getElementById("title-description-create").value;
      let genre = document.getElementById("title-genre-create").value;
      let type = document.getElementById("title-type-create").value;

      console.log(genre)

      const msg = document.getElementById("msg-create-title");

      let goodUrl =
        "/titles/create/" + titleName + "?description=" + description + "&genre=" + genre + "&isAvailable=true" + "&titleType=" + type;

      
      if (titleName.trim() === "") {
        msg.innerHTML = "Title cannot be empty!";
        msg.style.color = "red";
        return
      }

      if (description.trim() === "") {
        msg.innerHTML = "Description cannot be empty!";
        msg.style.color = "red";
        return
      }

      if (genre === "Genre") {
        console.log("shalom gabbyggggg")

        msg.innerHTML = "Genre cannot be empty!";
        msg.style.color = "red";
        return
      }

      if (type === "Type") {
        console.log("shalom gabbay again")
        msg.innerHTML = "TitleType cannot be empty!";
        msg.style.color = "red";
        return
      }
       

       AXIOS.post(goodUrl, {}, {})
        .then(response => {

          console.log("yoyoyo");
          // display success
          msg.innerHTML = "Title created successfully!";
          msg.style.color = "green";
          //console.log(msg);
        })
        .catch(e => {
          
          
          // display error
          //console.log(e)
          
        });
    },

    expandInventory() {
      const content = document.getElementById("inventory");
      const icon = document.getElementById("arrow-inventory");

      if (content.style.display == "none") {
        content.style.display = "block";
        icon.innerHTML = "-";
      } else {
        content.style.display = "none";
        icon.innerHTML = "+";
      }
    },

    expandStaffSchedule() {
      const content = document.getElementById("staff-schedule");
      const icon = document.getElementById("arrow-staff");
      if (content.style.display == "none") {
        content.style.display = "block";
        icon.innerHTML = "-";
      } else {
        content.style.display = "none";
        icon.innerHTML = "+";
      }
    },

    expandCheckout() {
      const content = document.getElementById("checkout-title");
      const icon = document.getElementById("arrow-checkout");
      if (content.style.display == "none") {
        content.style.display = "block";
        icon.innerHTML = "-";
      } else {
        content.style.display = "none";
        icon.innerHTML = "+";
      }
    },

    checkoutTitle() {
      const titleName = document.getElementById("title-name").value;
      const titleNameMsg = document.getElementById("title-name-msg");
      
      const clientUsernameMsg = document.getElementById("client-username-msg")
      const clientUsername = document.getElementById("client-username").value;
      
      const checkOutTitlemsg = document.getElementById("title-checkout-msg")
      
      clientUsernameMsg.innerHTML=""
      titleNameMsg.innerHTML=""
      checkOutTitlemsg.innerHTML=""

      if (clientUsername.trim() === ""){
        clientUsernameMsg.innerHTML = "Client username cannot be empty"
        clientUsernameMsg.style.color = "red";
        return
      }

      if (titleName.trim() === ""){
        titleNameMsg.innerHTML = "Title name cannot be empty"
        titleNameMsg.style.color = "red";
        return
      }

      let goodUrl =
        "/titles/checkout/" + titleName + "?clientUsername=" + clientUsername;

      AXIOS.post(goodUrl, {}, {})
        .then(response => {
          console.log(response.data);
          checkOutTitlemsg.innerHTML = "Title checkout Succesful"
          checkOutTitlemsg.style.color ="green"
      
        })
        .catch(e => {
          var errorMsg = e.response.data.message;
          checkOutTitlemsg.innerHTML = "Title checkout failed. Make sure the client and title are in the system and that the title is not already checked out."
          checkOutTitlemsg.style.color ="red"
        });
    },

    displaySuccess() {
      // const successMsg = document.querySelector(".success-msg")
      // const errorMsg = document.querySelector(".error-msg")
      // const error = false
      // if (error){
      //     errorMsg.hidden = false
      //     errorMsg.className += " fadeIn"
      // }
      // else {
      //     successMsg.hidden = false
      //     successMsg.className += " fadeIn"
      // }
      // const getinfo = document.getElementsByClassName("title-info")
      // console.log(getinfo)
      // Array.from(getinfo).forEach(element => {
      //     if(element.tagName != 'SELECT' ){
      //         element.value = ""
      //     }
      // })
    },
    deleteTitle() {
      let titleId = document.getElementById("title-id-delete").value;
      let goodUrl = "/titles/remove/" + titleId;

      const removeTitleMessage = document.getElementById("msg-delete-title");



      console.log(goodUrl);
      AXIOS.post(goodUrl, {}, {})
        .then(response => {
          removeTitleMessage.innerHTML = "Title removed succesfully";
          removeTitleMessage.style.color = "green";
        })
        .catch(e => {
          removeTitleMessage.innerHTML = "Title does not exist! Please provide an existing title Id";
          removeTitleMessage.style.color = "red";
        });
    },
    updateTitle() {
      let titleName = document.getElementById("title-name-update").value;
      console.log(titleName);
      let titleDescription = document.getElementById("title-description-update")
        .value;
      console.log(titleDescription);
      let titleType = document.getElementById("title-type-update").value;
      console.log(titleType);
      let titleGenre = document.getElementById("title-genre-update").value;
      console.log(titleGenre);

      const updateTitleMessage = document.getElementById("msg-update-title");


      let goodUrl =
        "/titles/update/" +
        titleName +
        "/?description=" +
        titleDescription +
        "&genre=" +
        titleGenre +
        "&titleType=" +
        titleType;
      console.log(goodUrl);


      if (titleName.trim() === "") {
        updateTitleMessage.innerHTML = "Title cannot be empty!";
        updateTitleMessage.style.color = "red";
        return
      }

      if (titleDescription.trim() === "") {
        updateTitleMessage.innerHTML = "Description cannot be empty!";
        updateTitleMessage.style.color = "red";
        return
      }

      if (titleGenre === "Genre") {
        updateTitleMessage.innerHTML = "Genre cannot be empty!";
        updateTitleMessage.style.color = "red";
        return
      }

      if (titleType === "TitleType") {
        updateTitleMessage.innerHTML = "TitleType cannot be empty!";
        updateTitleMessage.style.color = "red";
        return
      }

      AXIOS.post(goodUrl, {}, {})
        .then(response => {
          updateTitleMessage.innerHTML = "Title updated succesfully";
        updateTitleMessage.style.color = "green";
        })

        

        //display success

        .catch(e => {
          //we handled all errors above except the title not existing
          //so if it enters the catch, that means the title is not in database
          //you cannot update a title that is not in the database so there is an error
          updateTitleMessage.innerHTML = "Title does not exist!";
          updateTitleMessage.style.color = "red";
        });
    }
  },
  components: {
    MenuBar
  }
};
