import axios from 'axios'
import MenuBar from '../components/MenuBar.vue'

var config = require('../../config')

var frontendUrl = 'http://' + config.dev.host + ':' + config.dev.port
var backendUrl = 'http://' + config.dev.backendHost + ':' + config.dev.backendPort

var AXIOS = axios.create({
  baseURL: backendUrl,
  headers: { 'Access-Control-Allow-Origin': frontendUrl }
})

function TitleDto (name, description, genre, isAvailable, titleType){
    this.name = name
    this.description = description
    this.genre = genre
    this.isAvailable = isAvailable
    this.titleType = titleType
}

    export default{

        data(){
            return {
                titles: [],
                newTitle: '',
                errorTitle: '',
                response: []
            }
        },
        created: function(){

            // Initializing persons from backend
            AXIOS.get('/titles/get')
            .then(response => {
            // JSON responses are automatically parsed.
            this.titles = response.data
            })
            .catch(e => {
            this.errorTitle = e
            })

        },
        
        methods:  {
            createTitle: function(name, description, genre, isAvailable, titleType){
                AXIOS.post('/titles/create'.concat(name, description, genre, isAvailable, titleType), {}, {}).then(response => {
                var t = new TitleDto(name, description, genre, isAvailable, titleType)
                this.titles.push(t)
                this.newTitle = ''
                })
                .catch(e => {
                    var errorMsg = e.response.data.message
                    console.log(errorMsg)
                    this.errorTitle = errorMsg
                  })
            },

            displaySuccess(){

                // parameters
                const titleName = document.querySelector(".title-name")
                const description = document.querySelector(".description")
                const genre = document.querySelector(".genre")
                const type = document.querySelector(".title-type")

                //AXIOS.post('/titles/create/', {name:})
                

                const successMsg = document.querySelector(".success-msg")
                const errorMsg = document.querySelector(".error-msg")
                const error = false
                if (error){
                    errorMsg.hidden = false
                    errorMsg.className += " fadeIn"
                }
                else {
                    successMsg.hidden = false
                    successMsg.className += " fadeIn"
                }
                const getinfo = document.getElementsByClassName("title-info")
                console.log(getinfo)
                Array.from(getinfo).forEach(element => {
                    if(element.tagName != 'SELECT' ){
                        element.value = ""
                    }
                })
                
            }
        },

    components: {
        MenuBar,
    }
}