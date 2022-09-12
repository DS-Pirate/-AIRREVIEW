<template>
	<div class="col maincards">
		<div class="card-1 card border-0 w-100 mb-5">
			<div class="wrapper">
				<div class="date">
					<span class="day">12</span>
					<span class="month">Aug</span>
					<span class="year">2016</span>
				</div>
				<div class="data">
					<div class="content">
						<div @click="read()">
							<span class="author">{{ card[0] }}</span>
							<h3 class="title"><a>{{ card[2] }}</a></h3>
							<p class="text">내용을 축약해서 이곳에 표시합니다 약 30자 정도 생각하고있습니다</p>
						</div>
						<label :for="props.cardinfo" class="menu-button"><span></span></label>
					</div>
					<input type="checkbox" :id="props.cardinfo" />
					<ul class="menu-content">
						<li>
							<a href="#" class="bi bi-bookmark"></a>
						</li>
						<li><a href="#" class="bi bi-suit-heart-fill"><span ref="favCount">47</span></a></li>
						<li><a href="#" class="bi bi-exclamation-lg"></a></li>
					</ul>
				</div>
			</div>
		</div>
	</div>
</template>

<script setup>
import { defineProps } from "vue";
import router from "@/router";
// const props = defineProps(['cardinfo'])

const props = defineProps({
  card: Object,
})

function read() {
  router.push(`/read?article=${props.card[1]}`)
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
		content: '';
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
	;
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

	input[type='checkbox'] {
		display: none;
	}

	input[type='checkbox']:checked+.menu-content {
		transform: translateY(-60px);
	}
}
.card-1 {
	.wrapper {
		background: url(https://images.unsplash.com/photo-1496979551903-46e46589a88b?ixlib=rb-0.3.5&ixid=eyJhcHBfaWQiOjEyMDd9&s=cda12b505afa1beb06e49d89014cbd65&auto=format&fit=crop&w=634&q=80) 20% 1% / cover no-repeat;
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
		width: 25px;
		text-align: center;
		cursor: pointer;

		span {
			width: 5px;
			height: 5px;
			background-color: lighten($black, 50%);
			color: lighten($black, 50%);
			position: relative;
			display: inline-block;
			border-radius: 50%;

			&::after,
			&::before {
				content: '';
				display: block;
				width: 5px;
				height: 5px;
				background-color: currentColor;
				position: absolute;
				border-radius: 50%;
			}

			&::before {
				left: -10px;
			}

			&::after {
				right: -10px;
			}
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
