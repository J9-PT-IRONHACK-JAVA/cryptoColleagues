/*
 
 We also have methods for retrieving data from server, in the case we access protected resources. 
 Because HttpOnly Cookies will be automatically sent along with HTTP requests, 
 so we just simply use Axios without caring JWT.
 
*/

import axios from 'axios';

const API_URL = 'http://localhost:8080/api/test/';

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
	return axios.get('http://localhost:8080/api/news/all');
}

const UserService = {
	getPublicContent,
	getUserBoard,
	getAdminBoard,
	getNews
};

export default UserService;
