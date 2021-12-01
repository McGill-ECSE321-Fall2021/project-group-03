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
        },
    methods: {
            updateUserEmail() {       
                let newEmail = document.getElementById("update-email").value

                let goodUrl = "/clients/update/address/" + username + "?email=" + newEmail
                AXIOS.get(goodUrl, {}).then(response => {
                    window.location.href = "/#/"
                })
                .catch(e => {
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
                
                const editPasswordMessage = document.getElementById("account-edit-password")
                const editPasswordContent = document.getElementById("update-password").value

                const editEmailMessage = document.getElementById("account-edit-email");
                const editEmailContent = document.getElementById("update-email").value;

                const editAddressMessage = document.getElementById("account-edit-address");
                const editAddressContent = document.getElementById("update-address").value;

                editPasswordMessage.innerHTML="";
                editEmailMessage.innerHTML=""
                editAddressMessage.innerHTML="";
                

                // if (editPasswordContent.trim() === ""){
                //     console.log("les boys")
                //     editPasswordMessage.innerHTML = "Password cannot be empty"
                //     editPasswordMessage.style.color="red"
                //     return
                // }

            
                // if (editEmailContent.trim() === ""){
                //     console.log("les boys")
                //     editEmailMessage.innerHTML = "Email cannot be empty"
                //     editEmailMessage.style.color="red"
                //     return
                // }

        
                // if (editAddressContent.trim() === ""){
                //     console.log("les boys")
                //     editAddressMessage.innerHTML = "Address cannot be empty"
                //     editAddressMessage.style.color="red"
                //     return
                // }



                document.getElementById("save-info").hidden = true
                let newPassword = document.getElementById("update-password").value
                let newEmail = document.getElementById("update-email").value
                let newAddress = document.getElementById("update-address").value
                let goodUsername = localStorage.getItem("Username")
                let goodFullName = localStorage.getItem("FullName") 
                
                this.updateInfo("Updating account information")
                
                setTimeout(() => {if (newPassword.trim() != "") {
                    let goodUrl = ""
                    console.log(localStorage.getItem("isLibrarian") )
                    if (localStorage.getItem("isLibrarian")  == "true") {
                        goodUrl = "/librarians/update/" + goodUsername + "?password=" + newPassword + "&fullName=" + goodFullName + "&residentialAddress="  + this.address + "&email=" + this.email + "&isResident=true&isOnline=true";
                    } else {
                        goodUrl = "/clients/update/password/" + goodUsername + "?password=" + newPassword
                    }
                    

                    AXIOS.post(goodUrl, {}, {}).then(response => {
                        const editSaveMessage = document.getElementById("account-edit-save");
                        // editSaveMessage.innerHTML = "Succesfully updated"
                        // editSaveMessage.style.color = "green"

                        })
                        .catch(e => {
                            var errorMsg = e.response.data.message
                            this.errorTitle = errorMsg
                        })

                    localStorage.setItem("Password", newPassword);
                }}, 500);

                setTimeout(() => {if (newEmail.trim() != "") {
                    let goodUrl = "/clients/update/email/" + goodUsername + "?email=" + newEmail

                    AXIOS.post(goodUrl, {}, {}).then(response => {}).catch(e => {
                        var errorMsg = e.response.data.message
                        this.errorTitle = errorMsg
                    })

                    localStorage.setItem("Email", newEmail);
                }}, 1500);

                setTimeout(() => {if (newAddress.trim() != "") {
                    let goodUrl = "/clients/update/address/" + goodUsername + "?residentialAddress=" + newAddress
                    console.log(goodUrl)

                    AXIOS.post(goodUrl, {}, {}).then(response => {}).catch(e => {
                            var errorMsg = e.response.data.message
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