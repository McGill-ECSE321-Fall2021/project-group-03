import MenuBar from '../components/MenuBar.vue'
import TitleDto from './tools'
import axios from 'axios'

var config = require('../../config')

var frontendUrl = 'http://' + config.dev.host + ':' + config.dev.port
var backendUrl = 'http://' + config.dev.backendHost + ':' + config.dev.backendPort

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
            const titleName = document.getElementById("title-name")
            let goodUrl = '/titles/reserve/'+ titleName
            AXIOS.post(goodUrl, {}, {}).then(response => {
                })
                .catch(e => {
                    var errorMsg = e.response.data.message
                    console.log(errorMsg)
                    this.errorReservation = errorMsg
                  })
        }
    }
}