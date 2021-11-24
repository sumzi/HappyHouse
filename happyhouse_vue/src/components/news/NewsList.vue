<template>
  <div class="text-center mt-8">
    <v-card
      max-width="500px"
      max-height="600px"
      class="mx-auto overflow-y-auto"
    >
      <v-card-title>
        <span class="text-h4 font-weight-light">부동산 관련 뉴스</span>
      </v-card-title>

      <v-list three-line>
        <template v-for="(article, index) in news">
          <news-list-row
            :key="index"
            :article="article"
            :num="index"
            :detailLink="detailLink[index]"
          >
          </news-list-row>

          <v-divider
            v-if="index != items.length - 1"
            :key="index"
            inset
          ></v-divider>
        </template>
      </v-list>
    </v-card>
  </div>
  <!-- pagenation 들어갈 곳 -->
</template>

<script>
import NewsListRow from "@/components/news/NewsListRow.vue";
import http from "@/util/http-common.js";
export default {
  name: "NewsList",
  components: {
    NewsListRow,
  },
  data() {
    return {
      items: [
        { header: "Today" },
        {
          avatar: "https://cdn.vuetifyjs.com/images/lists/1.jpg",
          title: "Brunch this weekend?",
          subtitle: `<span class="text--primary">Ali Connors</span> &mdash; I'll be in your neighborhood doing errands this weekend. Do you want to hang out?`,
        },
        { divider: true, inset: true },
        {
          avatar: "https://cdn.vuetifyjs.com/images/lists/2.jpg",
          title: 'Summer BBQ <span class="grey--text text--lighten-1">4</span>',
          subtitle: `<span class="text--primary">to Alex, Scott, Jennifer</span> &mdash; Wish I could come, but I'm out of town this weekend.`,
        },
        { divider: true, inset: true },
        {
          avatar: "https://cdn.vuetifyjs.com/images/lists/3.jpg",
          title: "Oui oui",
          subtitle:
            '<span class="text--primary">Sandra Adams</span> &mdash; Do you have Paris recommendations? Have you ever been?',
        },
        { divider: true, inset: true },
        {
          avatar: "https://cdn.vuetifyjs.com/images/lists/4.jpg",
          title: "Birthday gift",
          subtitle:
            '<span class="text--primary">Trevor Hansen</span> &mdash; Have any ideas about what we should get Heidi for her birthday?',
        },
        { divider: true, inset: true },
        {
          avatar: "https://cdn.vuetifyjs.com/images/lists/5.jpg",
          title: "Recipe to try",
          subtitle:
            '<span class="text--primary">Britta Holt</span> &mdash; We should eat this: Grate, Squash, Corn, and tomatillo Tacos.',
        },
      ],
      news: [],
      detailLink: [],
    };
  },
  created() {
    http
      .get("/news/list", {})
      .then((response) => {
        console.log(response);
        this.news = response.data.news;
        this.detailLink = response.data.detailLink;
      })
      .catch((error) => {
        console.log(error);
      });
  },
  computed: {
    // ...mapState(dealStore, ["houses", "avgPrice"]),
    // // houses() {
    // //   return this.$store.state.houses;
    // // },
  },
};
</script>

<style></style>
