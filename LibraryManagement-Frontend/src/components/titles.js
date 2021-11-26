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
            

            createTitle: function (titleName) {
                AXIOS.post('/titles1/create'.concat(titleName), {}, {})
                  .then(response => {
                  // JSON responses are automatically parsed.
                    this.titles.push(response.data)
                    this.errorPerson = ''
                  })
                  .catch(e => {
                    var errorMsg = e.response.data.message
                    console.log(errorMsg)
                    this.errorPerson = errorMsg
                  })
              }
               // name: 'name',
                    // description: 'description',
                    // genre: 'genre',
                    // isAvailable: 'isAvailable',
                    // titleType: 'titleType'
        }
    }