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
            titleReservations: [],
            roomReservations: []
        }
    },

    name: 'Account',
    components: {
        MenuBar
    },

    mounted: function loadPage(){
        const username = localStorage.getItem("Username")
        const isLibrarian = localStorage.getItem("isLibrarian") 

        if (isLibrarian == "false") {
            let url = "/clients/get/reservations/" + username
            AXIOS.get(url)
            .then(response => {
                // JSON responses are automatically parsed.
                this.titleReservations = response.data
                console.log(this.titleReservations)
            })
            .catch(e => {
                this.errorTitle = e
            })

            // let goodUrl = "/clients/get/reservations/" + username
            // AXIOS.get(goodUrl).then(response => {
            //     // JSON responses are automatically parsed.
            //     this.roomReservations = response.data
            //     console.log(this.titleReservations)
            // }).catch(e => {
            //     this.errorTitle = e
            // })
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
    }
};