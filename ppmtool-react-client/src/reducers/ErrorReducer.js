import { GET_ERRORS } from '../actions/Types';

const initialState = {};

export default function (state = initialState, action) {
    switch (action.type) {
        case GET_ERRORS:
            console.log("Error Reducer is called in GET_ERRORS.");
            return action.payload;
        default:
            console.log("Error Reducer is called in default.");
            return state;
    }
}
