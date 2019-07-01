import { combineReducers } from "redux";
import ErrorReducer from "./ErrorReducer";
import ProjectsReducer from "./ProjectsReducer";

console.log("index.js reducer is started.");

export default combineReducers({
    errors: ErrorReducer,
    project: ProjectsReducer
});