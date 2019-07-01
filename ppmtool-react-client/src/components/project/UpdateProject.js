import React, { Component } from 'react';
import { getProject, createProject } from '../../actions/ProjectActions';
import PropTypes from 'prop-types';
import { connect } from 'react-redux';
import classnames from 'classnames';

class UpdateProject extends Component {

    constructor() {
        super();
        console.log("Update Project");
        this.state = {
            id: "",
            projectName: "",
            projectIdentifier: "",
            description: "",
            start_date: "",
            last_date: "",
            errors: {}
        };
    }

    componentDidMount() {
        const { id } = this.props.match.params;
        // this.setState({projectIdentifier: id});
        this.props.getProject(id, this.props.history);
    }

    componentWillReceiveProps(nextProps) {
        if (nextProps.errors) {
            this.setState({ errors: nextProps.errors });
        }
        const {
            id,
            projectName,
            projectIdentifier,
            description,
            start_date,
            last_date } = nextProps.project;

        this.setState({
            id,
            projectName,
            projectIdentifier,
            description,
            start_date,
            last_date
        });
    }

    handleChange = (event) => {
        this.setState({ [event.target.name]: event.target.value });
    }

    handleSubmit = (event) => {
        event.preventDefault();
        const updateProject = {
            id: this.state.id,
            projectName: this.state.projectName,
            projectIdentifier: this.state.projectIdentifier,
            description: this.state.description,
            start_date: this.state.start_date,
            last_date: this.state.last_date
        }
        console.log("CreateProject is started.");
        this.props.createProject(updateProject, this.props.history);
    }

    render() {
        const { errors } = this.state;

        return (
            <div>
                <div className="project">
                    <div className="container">
                        <div className="row">
                            <div className="col-md-8 m-auto">
                                <h5 className="display-4 text-center">Update Project form</h5>
                                <hr />
                                <form onSubmit={this.handleSubmit}>
                                    <div className="form-group">
                                        <input
                                            type="text"
                                            className={classnames("form-control form-control-lg", {
                                                "is-invalid": errors.projectName
                                            })}
                                            placeholder="Project Name"
                                            name="projectName"
                                            value={this.state.projectName}
                                            onChange={this.handleChange}
                                        />
                                        {errors.projectName && (
                                            <div className="invalid-feedback" style={{ fontSize: '16px' }}>
                                                {errors.projectName}
                                            </div>
                                        )}
                                    </div>
                                    <div className="form-group">
                                        <input
                                            type="text"
                                            className={classnames("form-control form-control-lg", {
                                                "is-invalid": errors.projectIdentifier
                                            })}
                                            placeholder="Unique Project ID"
                                            name="projectIdentifier"
                                            value={this.state.projectIdentifier}
                                            onChange={this.handleChange}
                                            disabled
                                        />
                                        {errors.projectIdentifier && (
                                            <div className="invalid-feedback" style={{ fontSize: '16px' }}>
                                                {errors.projectIdentifier}
                                            </div>
                                        )}
                                    </div>
                                    <div className="form-group">
                                        <textarea
                                            className={classnames("form-control form-control-lg", {
                                                "is-invalid": errors.description
                                            })}
                                            placeholder="Project Description"
                                            name="description"
                                            value={this.state.description}
                                            onChange={this.handleChange}>
                                        </textarea>
                                        {errors.description && (
                                            <div className="invalid-feedback" style={{ fontSize: '16px' }}>
                                                {errors.description}
                                            </div>
                                        )}
                                    </div>
                                    <h6>Start Date</h6>
                                    <div className="form-group">
                                        <input
                                            type="date"
                                            className="form-control form-control-lg"
                                            name="start_date"
                                            value={this.state.start_date}
                                            onChange={this.handleChange}
                                        />
                                    </div>
                                    <h6>Estimated End Date</h6>
                                    <div className="form-group">
                                        <input
                                            type="date"
                                            className="form-control form-control-lg"
                                            name="last_date"
                                            value={this.state.last_date}
                                            onChange={this.handleChange}
                                        />
                                    </div>
                                    <input type="submit" className="btn btn-primary btn-block mt-4" />
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        );
    }
}

UpdateProject.propTypes = {
    getProject: PropTypes.func.isRequired,
    createProject: PropTypes.func.isRequired,
    project: PropTypes.object.isRequired,
    errors: PropTypes.object.isRequired
}

const mapStateToProps = (state) => ({
    project: state.project.project,
    errors: state.errors
});

export default connect(mapStateToProps, { getProject, createProject })(UpdateProject);