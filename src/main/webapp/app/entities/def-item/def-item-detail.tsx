import React, { useEffect } from 'react';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { getEntity } from './def-item.reducer';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

export const DefItemDetail = (props: RouteComponentProps<{ id: string }>) => {
  const dispatch = useAppDispatch();

  useEffect(() => {
    dispatch(getEntity(props.match.params.id));
  }, []);

  const defItemEntity = useAppSelector(state => state.defItem.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="defItemDetailsHeading">
          <Translate contentKey="startpointApp.defItem.detail.title">DefItem</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="startpointApp.defItem.id">Id</Translate>
            </span>
          </dt>
          <dd>{defItemEntity.id}</dd>
          <dt>
            <span id="code">
              <Translate contentKey="startpointApp.defItem.code">Code</Translate>
            </span>
          </dt>
          <dd>{defItemEntity.code}</dd>
          <dt>
            <span id="name">
              <Translate contentKey="startpointApp.defItem.name">Name</Translate>
            </span>
          </dt>
          <dd>{defItemEntity.name}</dd>
          <dt>
            <Translate contentKey="startpointApp.defItem.type">Type</Translate>
          </dt>
          <dd>{defItemEntity.type ? defItemEntity.type.id : ''}</dd>
          <dt>
            <Translate contentKey="startpointApp.defItem.parent">Parent</Translate>
          </dt>
          <dd>{defItemEntity.parent ? defItemEntity.parent.code : ''}</dd>
        </dl>
        <Button tag={Link} to="/def-item" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/def-item/${defItemEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default DefItemDetail;
