import axios from 'axios'
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
            }
        }
    }