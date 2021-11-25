<template>
    <div>
        <MenuBar />
        <div class="header">Create Title</div>
        <div class="content">
            <form action="">
                <input type="text" placeholder="Name">
                <input type="text" placeholder="Description">
                <input type="text" placeholder="Genre">
                <select name="type">
                        <option value="book">Book</option>
                        <option value="movie">Movie</option>
                        <option value="music-album">Music Album</option>
                        <option value="newspaper">Newspaper</option>
                        <option value="archive">Archive</option>
                </select>
            </form>
        </div>
    </div>
</template>
<script>
import MenuBar from '../components/MenuBar.vue'

export default {
    components: {
        MenuBar,
    }
}
</script>

<style scoped>
</style>
