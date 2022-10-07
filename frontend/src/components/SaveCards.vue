<template>
	<div class="col maincards">
			<div class="card-1 card border-0 w-100 mb-5">
					<div class="wrapper" :style="'background-image: url(' + store.state.axiosLink + '/images/read/' + props.imageList[0].fileName + '); background-size:cover;'">
							<div class="date">
									<span class="day">{{props.card.regdate.split("-")[2].split("T")[0]}}</span>
									<span class="month">{{props.card.regdate.split("-")[1]}}</span>
									<span class="year">{{props.card.regdate.split("-")[0]}}</span>
							</div>
							<div class="data">
									<div class="content">
											<div @click="read()">
													<span class="author">{{ props.name }}</span>
													<h3 class="title">
														<h3 class="title"><a>{{ props.card.atitle }}</a></h3>
													</h3>
													<p class="text" v-html="props.card.context.length>30?props.card.context.slice(0,33):props.card.context"></p>
											</div>
											<input type="checkbox" :id="props.card" />
											<div class="menu-button"><span>조회수 {{card.opencount}}</span></div>
											</div>
											<input type="checkbox" :id="props.card" />
									</div>
							</div>
					</div>
			</div>
</template>

<script setup>
import { defineProps } from "vue";
import store from '@/store';
import router from "@/router";
let props = defineProps(["card", "name", "imageList"]);
console.log(props.imageList);

function read() {
  router.push(`/read?article=${props.card.aid}`);
}
</script>

<style scoped lang="scss">
	$regal-blue: #034378;
	$san-juan: #2d4e68;
	$white: #fff;
	$black: #000;
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

	h1 {
			font-weight: 200;
	}

	h2 {
			font-weight: 200;
	}

	h3 {
			font-weight: 200;
	}
	.row {
			margin: 50px auto 0;
	}
	.card {
			float: left;
			padding: 0 1.7rem;

			.menu-content {
					@include cf;
					margin: 0;
					padding: 0;
					list-style-type: none;

					li {
							display: inline-block;
					}

					a {
							color: $white;
					}

					span {
							position: absolute;
							left: 50%;
							top: 0;
							font-size: 0.6rem;
							font-weight: 700;
							transform: translate(-50%, 0);
					}
			}

			.wrapper {
					background-color: $white;
					min-height: 380px;
					position: relative;
					overflow: hidden;
					box-shadow: 0 7px 30px rgba($black, 0.3), 0 15px 12px rgba($black, 0.2);

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
			}

			input[type="checkbox"] {
					display: none;
			}

			input[type="checkbox"]:checked + .menu-content {
					transform: translateY(-60px);
			}
	}
	.card-1 {
			// .wrapper {
			// 		background: url(https://images.unsplash.com/photo-1496979551903-46e46589a88b?ixlib=rb-0.3.5&ixid=eyJhcHBfaWQiOjEyMDd9&s=cda12b505afa1beb06e49d89014cbd65&auto=format&fit=crop&w=634&q=80) 20% 1% / cover no-repeat;
			// }

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
							text-shadow: 2px 3px 2px rgba($black, 0.18);
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
					background-color: $white;
					box-shadow: 0 2.5px 20px 5px rgba($black, 0.3);
			}

			.title {
					a {
							color: lighten($black, 50%);
					}
			}

			.menu-button {
					position: absolute;
					z-index: 999;
					top: 16px;
					right: 16px;
					span {
							color: lighten($black, 50%);
							font-size: 10px;
					}
			}
			.menu-content {
					text-align: center;
					position: absolute;
					top: 0;
					left: 0;
					width: 100%;
					z-index: -1;
					transition: transform 0.3s;
					transform: translateY(0);

					li {
							width: 33.333333%;
							float: left;
							background-color: lightgray;
							height: 60px;
							position: relative;
					}

					a {
							position: absolute;
							top: 50%;
							left: 50%;
							transform: translate(-50%, -50%);
							font-size: 1.75rem;
					}

					span {
							top: -10px;
					}
			}
	}
</style>
