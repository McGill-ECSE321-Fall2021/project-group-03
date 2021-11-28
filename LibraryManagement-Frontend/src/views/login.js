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
            user: [],
            errorLogin: '',
            response: []
        }
    },

    name: 'Login',
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
            if (isLibrarian.checked){
                AXIOS.get('/librarians/login/')
                .then(response => {
                    this.users = response.data
                })
                .catch(e => {
                    this.errorLogin = e
                })

                localStorage.setItem("isLibrarian", false);
            }

            else {
                let username = document.getElementById("login-username").value
                console.log(username)
                let password = document.getElementById("login-password").value
                console.log(password)


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
                    alert("Account with this username and password does not exist")
                    console.log(e)
                    this.errorLogin = e
                })
            }

            
        }
    }
}