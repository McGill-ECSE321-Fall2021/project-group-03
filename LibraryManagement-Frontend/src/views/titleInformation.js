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
        const titleName = location.href.split("/")[6]
        console.log(titleName)
        let titles = []
        AXIOS.get('/titles/get/')
        .then(response => {
            titles = response.data
            titles.forEach(t => {
                if (t.name == titleName){
                    this.title = t
                    
                }
            });
            document.getElementById("title-type").innerHTML = this.title.titleType
            document.getElementById("title-description").innerHTML = this.title.description
            document.getElementById("title-genre").innerHTML = this.title.genre

        })
        .catch(e => {
        this.errorLogin = e
        })
    }
}