import React from 'react';

const Spinner = (props) => {
    return (
        <div className="text-center">
            <div className="spinner-border" role="status">
                <span className="sr-only">Loading...</span>
            </div>
        </div>
    );
}

Spinner.defaultProps = {
    message: "Loading..."
}

export default Spinner;