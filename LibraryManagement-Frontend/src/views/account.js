import MenuBar from '../components/MenuBar.vue'
import axios from 'axios'

var config = require('../../config')

var frontendUrl = 'http://' + config.dev.host + ':' + config.dev.port
var backendUrl = 'http://' + config.dev.backendHost + ':' + config.dev.backendPort

let username = localStorage.getItem("Username")
let password = localStorage.getItem("Password")
let email = localStorage.getItem("Email")
let address = localStorage.getItem("Address")

var AXIOS = axios.create({
  baseURL: backendUrl,
  headers: { 'Access-Control-Allow-Origin': frontendUrl }
})

export default{
    data(){
        
        return {
            users: [],
            errorLogin: '',
            response: []
        }
    },

    name: 'Account',
    components: {
        MenuBar
    },

    mounted: function displayClientTitleReservations(){
        // AXIOS.get('/titles/get')
        //     .then(response => {
        //     // JSON responses are automatically parsed.
        //     this.titles = response.data
        //     // console.log(this.titles)
        //     })
        //     .catch(e => {
        //     this.errorTitle = e
        //     })
    },
    methods: {
            updateUserEmail() {     
                  
                console.log(username)      
                let newEmail = document.getElementById("update-email").value
                console.log(newEmail)

                // HOW DO U GET USERNAME OF WHO IS LOGGED IN 
                let goodUrl = "/clients/update/address/" + username + "?email=" + newEmail
                console.log(goodUrl)
                AXIOS.get(goodUrl, {}).then(response => {
                    window.location.href = "/#/"
                })
                .catch(e => {
                    alert("Account with this username and password does not exist")
                    console.log(e)
                    this.errorLogin = e
                })
            },

            showPass() {
                const password = document.querySelector(".show-pass");
                const passwordHide = document.querySelector(".password-hidden");
                const passwordContent = document.querySelector(".password");
                if (password.checked) {
                  // display password
                  passwordContent.hidden = false;
                  passwordHide.hidden = true;
                } else {
                  // hide password
                  passwordContent.hidden = true;
                  passwordHide.hidden = false;
                }
              },
          
            editUsername() {
                const save = document.querySelector(".save");
          
                if (save.hidden) {
                  document.querySelector(".save").hidden = false;
                  document.querySelector(".save").className += " fadeIn";
                }
                document.querySelector(".edit-username").hidden = false;
                document.querySelector(".edit-username").className += " fadeIn";
            },
          
            editPassword() {
                const save = document.querySelector(".save");
          
                if (save.hidden) {
                  document.querySelector(".save").hidden = false;
                  document.querySelector(".save").className += " fadeIn";
                }
                document.querySelector(".edit-password").hidden = false;
                document.querySelector(".edit-password").className += " fadeIn";
            },
          
            editEmail() {
                const save = document.querySelector(".save");
          
                if (save.hidden) {
                  document.querySelector(".save").hidden = false;
                  document.querySelector(".save").className += " fadeIn";
                }
                document.querySelector(".edit-email").hidden = false;
                document.querySelector(".edit-email").className += " fadeIn";
            },
          
            editAddress() {
                const save = document.querySelector(".save");
          
                if (save.hidden) {
                  document.querySelector(".save").hidden = false;
                  document.querySelector(".save").className += " fadeIn";
                }
                document.querySelector(".edit-address").hidden = false;
                document.querySelector(".edit-address").className += " fadeIn";
            },
          
            saveInfo() {
                // document.querySelector(".edit-username").hidden = true;
                // document.querySelector(".edit-password").hidden = true;
                // document.querySelector(".edit-email").hidden = true;
                // document.querySelector(".edit-address").hidden = true;
                // document.querySelector(".save").hidden = true;

                let newPassword = document.getElementById("update-password").value
                let newEmail = document.getElementById("update-email").value
                let newAddress = document.getElementById("update-address").value

                if (newPassword != "") {
                    let goodUrl = "/clients/update/password/" + username + "?password=" + newPassword
                    console.log(goodUrl)

                    AXIOS.post(goodUrl, {}, {}).then(response => {
                        
                        })
                        .catch(e => {
                            var errorMsg = e.response.data.message
                            console.log(errorMsg)
                            this.errorTitle = errorMsg
                        })
                }

                if (newEmail != "" && newEmail != null ) {
                    let goodUrl = "/clients/update/email/" + username + "?email=" + newEmail
                    console.log(goodUrl)

                    AXIOS.post(goodUrl, {}, {}).then(response => {
                        
                        })
                        .catch(e => {
                            var errorMsg = e.response.data.message
                            console.log(errorMsg)
                            this.errorTitle = errorMsg
                        })
                }

                if (newAddress != "") {
                    let goodUrl = "/clients/update/address/" + username + "?residentialAddress=" + newAddress
                    console.log(goodUrl)

                    AXIOS.post(goodUrl, {}, {}).then(response => {
                        
                        })
                        .catch(e => {
                            var errorMsg = e.response.data.message
                            console.log(errorMsg)
                            this.errorTitle = errorMsg
                        })
                }

                newPassword = "";
                newEmail = "";
                newAddress = "";
            },

            displayUsername() {
                return username;
            },

              displayPassword() {
                return password;
            },

            displayEmail() {
                return email;
            },

            displayAddress() {
                return address;
            }
    }
};