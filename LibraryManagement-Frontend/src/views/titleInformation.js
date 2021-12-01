import MenuBar from '../components/MenuBar.vue'
import axios from 'axios'

var config = require('../../config')

var frontendUrl = 'http://' + config.dev.host + ':' + config.dev.port
var backendUrl = 'http://' + config.dev.backendHost + ':' + config.dev.backendPort
var title;

var AXIOS = axios.create({
  baseURL: backendUrl,
  headers: { 'Access-Control-Allow-Origin': frontendUrl }
})


export default {
    name: 'Home',
    components: {
    MenuBar
    },

    data(){
        return {
            title: []
        }
      },

    methods :{
        reserveTitle(){

            // create title reservation
            const titleName = document.getElementById("title-name").innerHTML
            const userLoggedIn = localStorage.getItem("Username")
            let msg = document.getElementById("msg")

            let goodUrl = '/titles/reserve/'+ titleName + "?clientUsername=" + userLoggedIn
            AXIOS.post(goodUrl, {}, {}).then(response => {
                    msg.style.color = "green"
                    msg.hidden = false
                    msg.innerHTML = "Successfully reserved the title"
                })
                .catch(e => {
                    var errorMsg = e.response.data.message
                    this.errorReservation = errorMsg
                    msg.style.color = "red"
                    msg.hidden = false
                    msg.innerHTML = "Cannot reserve title"
                  })
        }
    },

    mounted: function load(){
        const titleName = document.getElementById("title-name").innerHTML
        let titles = []
        AXIOS.get('/titles/get/').then(response => {
            titles = response.data
            titles.forEach(t => {                
                if (t.name == titleName){
                    document.getElementById("title-type").innerHTML = t.titleType
                    document.getElementById("title-description").innerHTML = t.description
                    document.getElementById("title-genre").innerHTML = t.genre
                }
            });

        })
        .catch(e => {
        this.errorLogin = e
        })

        if (localStorage.getItem("isLibrarian") == "true") {
            document.getElementById("reserve-title-btn").hidden = true;
        }
    }
}