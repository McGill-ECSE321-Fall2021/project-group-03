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
            roomReservations: [],
            errorLogin: '',
            response: [],
        }
    },
    created: function(){
        let reservations = []
        let roomId = localStorage.getItem("roomId");
        let goodUrl = '/rooms/getRoomReservationByRoom/' + roomId

        AXIOS.get(goodUrl).then(response => {
            for (let i = 0; i < response.data.length; i++) {
                if (response.data[i].client.fullName == "lee") {
                    reservations.push(response.data[i]);
                }
            }
            // console.log(reservations)
            reservations.sort(function(a, b) {
                return new Date(a.date) - new Date(b.date);
            })
            this.roomReservations = reservations
        }).catch(e => {
            this.errorTitle = e
        })
    },

    name: 'Account',
    components: {
        MenuBar
    },

    methods: {
        clickRow(roomReservation) {
            let userId = localStorage.getItem("userId")
            let goodUrl = "/roomReservations/update/" + roomReservation.roomReservationID + "?userId=" + userId;
            AXIOS.post(goodUrl).then(response => {
                console.log(response.data)
                this.roomReservations = response.data
            })
            .catch(e => {
                this.errorTitle = e
            })
            
            window.location.href = "/#/";
        }
    }
};