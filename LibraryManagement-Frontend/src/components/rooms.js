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
                rooms: [], 
                newRoom: '', 
                errorRoom: '', 
                response: [] 
            }
        },
        created: function(){
            console.log("created")
            // Initializing persons from backend
            AXIOS.get('/rooms/get')
            .then(response => {
            // JSON responses are automatically parsed.
            this.rooms = response.data
            console.log("hi")
            console.log(this.rooms)
            })
            .catch(e => {
            this.errorTitle = e
            })

        },
        
        methods:  {
            createRoom: function(capacity, isAvailable, roomType){
                AXIOS.post('/rooms/create'.concat(capacity, isAvailable, roomType), {}, {}).then(response => {
                var roomDto = new RoomDto(capacity, isAvailable, roomType)
                this.rooms.push(roomDto)
                this.newRoom = ''
                })
                .catch(e => {
                    var errorMsg = e.response.data.message
                    console.log(errorMsg)
                    this.errorTitle = errorMsg
                  })
            }
        },
        
        
    }