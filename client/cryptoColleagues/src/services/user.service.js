/*
 
 We also have methods for retrieving data from server, in the case we access protected resources. 
 Because HttpOnly Cookies will be automatically sent along with HTTP requests, 
 so we just simply use Axios without caring JWT.
 
*/

import axios from 'axios';

const API_URL = 'http://localhost:3000/api/test/';

const getPublicContent = () => {
	return axios.get(API_URL + 'all');
};

const getUserBoard = () => {
	return axios.get(API_URL + 'user');
};

const getAdminBoard = () => {
	return axios.get(API_URL + 'admin');
};

const getNews = () => {
	return axios.get('http://localhost:3000/api/news/all');
}

const getCryptos = () => {
	return axios.get('http://localhost:3000/api/cryptocurrencies/all');
}

const getPortfolio = (userId) => {
	return axios.get(`http://localhost:3000/api/portfolios/${userId}`);
}

const getPosts = (userId) => {
	return axios.get(`http://localhost:3000/api/posts/all`);
}

const UserService = {
	getPublicContent,
	getUserBoard,
	getAdminBoard,
	getNews,
	getCryptos,
	getPortfolio,
	getPosts
};

export default UserService;
