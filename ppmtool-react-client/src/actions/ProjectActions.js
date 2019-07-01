import axios from "axios";
import { GET_ERRORS, GET_PROJECTS, GET_PROJECT, DELETE_PROJECT } from './Types';

export const createProject = (project, history) => async dispatch => {
    try {
        await axios.post('/api/projects', project);
        history.push('/dashboard');
        dispatch({
            type: GET_ERRORS,
            payload: {}
        });
    } catch (error) {
        dispatch({
            type: GET_ERRORS,
            payload: error.response.data
        });
    }
    console.log("createProject ended");
}

export const getAllProjects = () => async dispatch => {
    try {
        const response = await axios.get('/api/projects');
        dispatch({
            type: GET_PROJECTS,
            payload: response.data
        });
    } catch (error) {
        console.log("ZZZZZZZZ " + error.response);
    }
    console.log("getAllProjects is ended.");
}

export const getProject = (id, history) => async dispatch => {
    try {
        const response = await axios.get(`/api/projects/${id}`);
        dispatch({
            type: GET_PROJECT,
            payload: response.data
        });
        console.log("getProject is ended.");
    } catch (error) {
        console.log("getProject is ended with errors.");
        history.push("/dashboard");
    }
}

export const deleteProject = (id) => async dispatch => {
    if (
        window.confirm(
            "Are you sure? This will delete the project and all the data related to it"
        )
    ) {
        console.log("ZZZZZ "+id+"XXXX");
        await axios.delete(`/api/projects/${id}`);
        dispatch({
            type: DELETE_PROJECT,
            payload: id
        });
    }
}
