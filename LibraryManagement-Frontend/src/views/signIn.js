import MenuBar from '../components/MenuBar.vue'
import axios from 'axios'

var config = require('../../config')

var frontendUrl = 'http://' + config.dev.host + ':' + config.dev.port
var backendUrl = 'http://' + config.dev.backendHost + ':' + config.dev.backendPort

var AXIOS = axios.create({
  baseURL: backendUrl,
  headers: { 'Access-Control-Allow-Origin': frontendUrl }
})

let errorMsg = "aw";

export default{
    data(){
        return {
            users: [],
            errorLogin: '',
            response: []
        }
    },

    name: 'SignIn',
    components: {
        MenuBar
    },
    methods: {
        fadeIn(){
            document.querySelector(".register-form").hidden = false
            document.querySelector(".register-form").className += " fade-in"
        },

        loginUser(){
            const isLibrarian = document.querySelector(".isLibrarian")

            let username = document.getElementById("login-username").value
            let password = document.getElementById("login-password").value


            if (username == "") {
                errorMsg = "Can't have an empty username"
                this.setErrorMsg(errorMsg)
                return
            }

            if (password == "") {
                errorMsg = "Can't have an empty password"
                this.setErrorMsg(errorMsg)
                return
            }

            if (isLibrarian.checked){
                
                let goodUrl = "/librarians/login/" + username + "?password=" + password
                console.log(goodUrl)
                AXIOS.get(goodUrl, {}).then(response => {
                    console.log(response.data)

                    let responseUser = response.data.username;
                    let responseEmail = response.data.email;
                    let responsePassword = response.data.password;
                    let responseAddress = response.data.residentialAddress;

                    window.location.href = "/#/"

                    localStorage.setItem("Username", responseUser);
                    localStorage.setItem("Email", responseEmail);
                    localStorage.setItem("Password", responsePassword);
                    localStorage.setItem("Address", responseAddress);
                    localStorage.setItem("isLibrarian", true);

                })
                .catch(e => {
                    errorMsg = "Librarian account with this username and password does not exist"
                    this.setErrorMsg(errorMsg)
                    console.log(e)
                    this.errorLogin = e
                })
            }

            else {
                let goodUrl = "/clients/login/" + username + "?password=" + password
                console.log(goodUrl)
                AXIOS.get(goodUrl, {}).then(response => {
                    console.log(response.data)

                    let responseUser = response.data.username;
                    let responseEmail = response.data.email;
                    let responsePassword = response.data.password;
                    let responseAddress = response.data.residentialAddress;

                    window.location.href = "/#/"

                    localStorage.setItem("Username", responseUser);
                    localStorage.setItem("Email", responseEmail);
                    localStorage.setItem("Password", responsePassword);
                    localStorage.setItem("Address", responseAddress);
                    localStorage.setItem("isLibrarian", false);

                })
                .catch(e => {
                    errorMsg = "Client account with this username and password does not exist"
                    this.setErrorMsg(errorMsg)
                    console.log(e)
                    this.errorLogin = e
                })
            }
            
            username = ""
            password = ""
        },

        createUser() {
            let fullName = document.getElementById("create-fullName").value
            let username = document.getElementById("create-username").value
            let password = document.getElementById("create-password").value
            let password2 = document.getElementById("create-password2").value
            let email = document.getElementById("create-email").value
            let address = document.getElementById("create-address").value
            let isResident = document.getElementById("create-isResident").value

            if (fullName == "") {
                errorMsg = "Can't have an empty name"
                this.setErrorMsg2(errorMsg)
                return
            }

            if (username == "") {
                errorMsg = "Can't have an empty username"
                this.setErrorMsg2(errorMsg)
                return
            }

            if (password == "") {
                errorMsg = "Can't have an empty password"
                this.setErrorMsg2(errorMsg)
                return
            }

            if (password2 != password) {
                errorMsg = "Passwords don't match"
                this.setErrorMsg2(errorMsg)
                return
            }

            if (email == "") {
                errorMsg = "Can't have an empty email"
                this.setErrorMsg2(errorMsg)
                return
            }

            if (address == "") {
                errorMsg = "Can't have an empty address"
                this.setErrorMsg2(errorMsg)
                return
            }

            let goodUrl = "/clients/create/" + username + "?password=" + password + "&fullName=" + fullName + "&residentialAddress=" + address + "&email=" + email + "&isResident=" + isResident + "&isOnline=true"
            console.log(goodUrl)

            AXIOS.post(goodUrl, {}, {}).then(response => {
            var t = new ClientDto(username, fullName, password, address, email, isResident, true)
            this.users.push(t)
            })
            .catch(e => {
                var errorMsg = e.response.data.message
                console.log(errorMsg)
                this.errorTitle = errorMsg
            })

            // console.log("here")
            address = ''
            email = ""
            password = ""
            password2 = ""
            username = ""
            fullName = ""
            
        },

        setErrorMsg(msg) {
            document.getElementById("error-msg").innerHTML = msg
        },

        setErrorMsg2(msg) {
            document.getElementById("error-msg2").innerHTML = msg
        }
    }
}