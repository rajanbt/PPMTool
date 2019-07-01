import { GET_PROJECTS, GET_PROJECT, DELETE_PROJECT } from '../actions/Types';

const initialState = {
    projects: [],
    project: {}
};

export default function (state = initialState, action) {
    switch (action.type) {
        case GET_PROJECTS:
            console.log("Project Reducer is called GET_PROJECTS.");
            return {
                ...state,
                projects: action.payload
            }
        case GET_PROJECT:
            console.log("Project Reducer is called GET_PROJECT.");
            return {
                ...state,
                project: action.payload
            }
        case DELETE_PROJECT:
            console.log("Project Reducer is called DELETE_PROJECT.");
            return {
                ...state,
                projects: state.projects.filter(project => project.projectIdentifier !== action.payload)
            }
        default:
            console.log("Project Reducer is called default.");
            return state;
    }
}