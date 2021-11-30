import MenuBar from '../components/MenuBar.vue'
import axios from 'axios'

var config = require('../../config')

var frontendUrl = 'http://' + config.dev.host + ':' + config.dev.port
var backendUrl = 'http://' + config.dev.backendHost + ':' + config.dev.backendPort

var AXIOS = axios.create({
  baseURL: backendUrl,
  headers: { 'Access-Control-Allow-Origin': frontendUrl }
})

export default{
    data(){
        
        return {
            users: [],
            errorLogin: '',
            response: [],
            reservations: []
        }
    },

    name: 'Account',
    components: {
        MenuBar
    },

    mounted: function loadPage(){
        const username = localStorage.getItem("Username")
        const password = localStorage.getItem("Password")
        const email = localStorage.getItem("Email")
        const address = localStorage.getItem("Address")
        const isLibrarian = localStorage.getItem("isLibrarian") 
        
        document.getElementById("user-username").innerHTML = username
        document.getElementById("user-password").innerHTML = password
        document.getElementById("user-email").innerHTML = email
        document.getElementById("user-address").innerHTML = address

        if(isLibrarian == "true"){
            document.getElementById("email-section").hidden = true
            document.getElementById("address-section").hidden = true
            document.getElementById("reservation-section").hidden = true
        }

        else {
            let url = "/clients/get/reservations/" + username
            AXIOS.get(url)
            .then(response => {
                // JSON responses are automatically parsed.
                this.reservations = response.data
                console.log(this.titleReservations)
            })
            .catch(e => {
                this.errorTitle = e
            })
        }
        },
    methods: {

            getClientReservations(){
                return this.titleReservations
            },

            displayAvailability: function(isAvailable) {
                if (isAvailable) {
                    return "✓"
                } else {
                    return "✗"
                }
            },

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

                document.getElementById("save-info").hidden = true
                let newPassword = document.getElementById("update-password").value
                let newEmail = document.getElementById("update-email").value
                let newAddress = document.getElementById("update-address").value
                
                this.updateInfo("Updating account information")
                

                setTimeout(() => {if (newPassword != "") {
                    let goodUrl = "/clients/update/password/" + username + "?password=" + newPassword
                    console.log(goodUrl)

                    AXIOS.post(goodUrl, {}, {}).then(response => {
                        
                        })
                        .catch(e => {
                            var errorMsg = e.response.data.message
                            console.log(errorMsg)
                            this.errorTitle = errorMsg
                        })

                    localStorage.setItem("Password", newPassword);
                }}, 500);

                setTimeout(() => {if (newEmail != "" && newEmail != null ) {
                    let goodUrl = "/clients/update/email/" + username + "?email=" + newEmail
                    console.log(goodUrl)

                    AXIOS.post(goodUrl, {}, {}).then(response => {
                        
                        })
                        .catch(e => {
                            var errorMsg = e.response.data.message
                            console.log(errorMsg)
                            this.errorTitle = errorMsg
                        })

                    localStorage.setItem("Email", newEmail);
                }}, 1500);

                setTimeout(() => {if (newAddress != "") {
                    let goodUrl = "/clients/update/address/" + username + "?residentialAddress=" + newAddress
                    console.log(goodUrl)

                    AXIOS.post(goodUrl, {}, {}).then(response => {
                        
                        })
                        .catch(e => {
                            var errorMsg = e.response.data.message
                            console.log(errorMsg)
                            this.errorTitle = errorMsg
                        })

                    localStorage.setItem("Address", newAddress);
                }}, 2000);


                setTimeout(() => {location.reload()}, 4000);
                
            },

            updateInfo(msg) {
                document.getElementById("loader").hidden = false
                document.getElementById("account-updating").innerHTML = msg;
            }
    }
};