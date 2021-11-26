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

                sessionStorage.setItem("sessionUser", users.stringify())
                sessionStorage.setItem("librarian", true)
                sessionStorage.setItem("client", false)
            }

            else {
                AXIOS.get('/clients/login/')
                .then(response => {
                    this.users = response.data
                })
                .catch(e => {
                    this.errorLogin = e
                })

                sessionStorage.setItem("sessionUser", users.stringify())
                sessionStorage.setItem("client", true)
                sessionStorage.setItem("librarian", false)

            }
        }
    }
}