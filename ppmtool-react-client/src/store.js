import { createStore, applyMiddleware, compose } from "redux";
import thunk from 'redux-thunk'
import rootReducers from './reducers'

console.log("Store is creation started.");
const initialState = {};
const middleware = [thunk];

let store;

if (window.navigator.userAgent.includes("Chrome")) {
    store = createStore(
        rootReducers,
        initialState,
        compose(applyMiddleware(...middleware), window.__REDUX_DEVTOOLS_EXTENSION__ &&
            window.__REDUX_DEVTOOLS_EXTENSION__())
        // compose(applyMiddleware(...middleware))
    );
} else {
    store = createStore(
        rootReducers,
        initialState,
        compose(applyMiddleware(...middleware))
    );
}

console.log("Store is creation ended.");

export default store;
