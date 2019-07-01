import React, { Component } from 'react';
import ProjectItem from './project/ProjectItem';
import CreateProjectButton from './project/CreateProjectButton';
import { connect } from 'react-redux';
import { getAllProjects } from '../actions/ProjectActions';
import PropTypes from 'prop-types';
import { ClipLoader } from 'react-spinners';
import { css } from '@emotion/core';

const override = css`
    display: block;
    margin: 0 auto;
    border-color: red;
`;

class Dashboard extends Component {
    constructor(props) {
        super(props);
        console.log("Dashboard is started.");
    }

    componentDidMount() {
        console.log("getAllProjects is started.");
        this.props.getAllProjects();
        console.log("Dashboard is ended.");
    }

    render() {
        const { projects } = this.props.project;
        // let loader;
        // if (projects.length === 0) {
        //     loader = <div></div>
        // } else {

        // }
        return (
            <div className="projects">
                <div className="container">
                    <div className="row">
                        <div className="col-md-12">
                            <h1 className="display-4 text-center">Projects</h1>
                            <br />
                            <CreateProjectButton />
                            <br />
                            <hr />

                            {/* {(() => {
                                if (projects.length === 0) {
                                    return <Spinner />
                                } else {
                                    projects.map((project) => (
                                        <ProjectItem key={project.id} project={project} />
                                    ))
                                }
                            })()} */}
                            {
                                (() => {
                                    if (projects.length === 0) {
                                        console.log("Dashboard is rendered with spinner");
                                        return (
                                            // <div class="text-center" style={{backgroundColor: "red"}}>
                                            //     <div class="spinner-border">
                                            //         <span class="sr-only">Loading...</span>
                                            //     </div>
                                            // </div>
                                            <div style={{ textAlign: "center" }}>
                                                <ClipLoader
                                                    css={override}
                                                    sizeUnit={"px"}
                                                    size={150}
                                                    color={'#123abc'}
                                                />
                                            </div>
                                        )
                                    } else {
                                        console.log("Dashboard is rendered with data");
                                        return projects.map((project) => (
                                            <ProjectItem key={project.id} project={project} />
                                        ))
                                    }
                                })()
                            }

                        </div>
                    </div>
                </div>
            </div>
        );
    }
}

Dashboard.propTypes = {
    getAllProjects: PropTypes.func.isRequired,
    project: PropTypes.object.isRequired
}

const mapStateToProps = (state) => ({
    project: state.project
});

export default connect(mapStateToProps, { getAllProjects })(Dashboard);