import MenuBar from '../components/MenuBar.vue'
import axios from 'axios'
import { BIconPause } from 'bootstrap-vue'

var config = require('../../config')

var frontendUrl = 'http://' + config.dev.host + ':' + config.dev.port
var backendUrl = 'http://' + config.dev.backendHost + ':' + config.dev.backendPort

var AXIOS = axios.create({
  baseURL: backendUrl,
  headers: { 'Access-Control-Allow-Origin': frontendUrl }
})

let errorMsg = "";

export default{
    data(){
        return {
            users: [],
            errorLogin: '',
            response: []
        }
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
                    let responseUserId = response.data.userId;

                    window.location.href = "/#/"

                    localStorage.setItem("Username", responseUser);
                    localStorage.setItem("Email", responseEmail);
                    localStorage.setItem("Password", responsePassword);
                    localStorage.setItem("Address", responseAddress);
                    localStorage.setItem("isLibrarian", false);
                    localStorage.setItem("userId", responseUserId);

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

            let canLogin = true;
            let goodUrl = "/clients/create/" + username + "?password=" + password + "&fullName=" + fullName + "&residentialAddress=" + address + "&email=" + email + "&isResident=" + isResident + "&isOnline=true"
            console.log(goodUrl)

            AXIOS.post(goodUrl, {}, {}).then(response => {
                
                setTimeout(() => {
                    let responseUserId = localStorage.getItem("userId");
                    var t = new ClientDto(responseUserId, username, fullName, password, address, email, isResident, true)
                    this.users.push(t)
                }, 1000)

            }).catch(e => {
                this.setErrorMsg2("Username already taken")
                canLogin = false;
            })

            address = ''
            email = ""
            password = ""
            password2 = ""
            username = ""
            fullName = ""

                
            setTimeout(() => {
                if (canLogin) {
                    this.loginNewUser()
                }
            }, 1000)
        },

        setErrorMsg(msg) {
            document.getElementById("error-msg").innerHTML = msg
        },

        setErrorMsg2(msg) {
            document.getElementById("error-msg2").innerHTML = msg
        },

        loginNewUser(){
            let username = document.getElementById("create-username").value
            let password = document.getElementById("create-password").value
            
            let goodUrl = "/clients/login/" + username + "?password=" + password
            console.log(goodUrl)
            AXIOS.get(goodUrl, {}).then(response => {
                console.log(response.data)

                let responseUser = response.data.username;
                let responseEmail = response.data.email;
                let responsePassword = response.data.password;
                let responseAddress = response.data.residentialAddress;
                let responseUserId = response.data.userId;

                window.location.href = "/#/"

                localStorage.setItem("Username", responseUser);
                localStorage.setItem("Email", responseEmail);
                localStorage.setItem("Password", responsePassword);
                localStorage.setItem("Address", responseAddress);
                localStorage.setItem("isLibrarian", false);
                localStorage.setItem("userId", responseUserId);

            })
            .catch(e => {
                console.log(e)
                this.errorLogin = e
            })
            
            
            username = ""
            password = ""
        },

        
    }
}