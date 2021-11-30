<template>
  <div>
    <MenuBar />
    <div class="header">{{ $route.params.name }}</div>
    <div class="header-2">Available Time Slots</div>
    <AvailableRoomsTable />
  </div>
</template>
<script>
import MenuBar from "../components/MenuBar.vue";
import AvailableRoomsTable from "../components/AvailableRoomsTable.vue";
export default {
  name: "Home",
  components: {
    MenuBar,
    AvailableRoomsTable,
  },
};
</script>
<style>
.header {
  /* text-align: center; */
  color: darkslategray;
  font-size: 35px;
  font-weight: 700;
  padding: 20px;
  padding-bottom: 0px;
  padding-top: 0px;
}

.content {
  padding-left: 20px;
  color: darkslategray;
  font-size: 15px;
  font-weight: 400;
}

.header-2 {
  color: darkslategray;
  font-size: 25px;
  font-weight: 450;
  text-align: center;
}
</style>