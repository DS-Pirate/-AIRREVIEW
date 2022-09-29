<template>
    <div class="col maincards">
        <div class="card-1 card border-0 w-100 mb-5">
            <div class="wrapper" ref="wrapper" :style="'background-image: url('+store.state.axiosLink+'/images/read/'+props.uImage+'); background-size:cover;'" >
                <div class="date">
                    <span class="day">{{props.cardInfo.regdate.split("-")[2].split("T")[0]}}</span>
                    <span class="month">{{props.cardInfo.regdate.split("-")[1]}}</span>
                    <span class="year">{{props.cardInfo.regdate.split("-")[0]}}</span>		
                </div>
				<a :href="'./read?article='+props.cardInfo.aid">
                    <div class="data">
                    <div class="content">
                            <span class="author">{{ props.uInfo }}</span>
                            <h3 class="title">
                                <a>{{ props.cardInfo.atitle.length>10?props.cardInfo.atitle.slice(0, 10):props.cardInfo.atitle }}</a>
                            </h3>
                            <p class="text" v-html="props.cardInfo.context"></p>
						</div>			
					</div>
				</a>
            </div>
        </div>
    </div>
</template>

<script setup>

    import store from "@/store";
    import { defineProps } from "vue";

    const props = defineProps(["cardInfo", "uInfo", "uImage"]);

</script>

<style scoped lang="scss">
    @mixin cf {
        &::before,
        &::after {
            content: "";
            display: table;
        }

        &::after {
            clear: both;
        }
    }

    * {
        box-sizing: border-box;
    }

    a {
        text-decoration: none;
    }
    .card {
        float: left;
        padding: 0 1.7rem;

        .wrapper {
            background-color: white;
            min-height: 380px;
            position: relative;
            overflow: hidden;
            box-shadow: 0 7px 30px rgba(black, 0.3), 0 15px 12px rgba(black, 0.2);

            &:hover {
                .data {
                    transform: translateY(0);
                }
            }
        }

        .data {
            position: absolute;
            bottom: 0;
            width: 100%;
            transform: translateY(calc(70px + 1em));
            transition: transform 0.3s;

            .content {
                padding: 1em;
                position: relative;
                z-index: 1;
            }
        }

        .author {
            font-size: 0.8rem;
        }

        .title {
            margin-top: 0.7rem;
        }

        .text {
            height: 4.5rem;
            margin: 0;
            color: black;
            white-space: nowrap;
        }

        input[type="checkbox"] {
            display: none;
        }
    }
    .card-1 {
        .wrapper {
            background: white
        }

        .date {
            position: absolute;
            top: 0;
            left: 0;
            background-color: lightgray;
            color: black;
            padding: 0.8em;

            span {
                display: block;
                text-align: center;
            }

            .day {
                font-weight: 700;
                font-size: 1.5rem;
                text-shadow: 2px 3px 2px rgba(black, 0.18);
            }

            .month {
                text-transform: uppercase;
            }

            .month,
            .year {
                font-size: 0.7rem;
            }
        }

        .content {
            background-color: white;
            box-shadow: 0 2.5px 20px 5px rgba(black, 0.3);
        }

        .title {
            a {
                color: lighten(black, 50%);
            }
        }
    }
</style>
