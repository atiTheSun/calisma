import React, { useState, useEffect } from 'react';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, FormText } from 'reactstrap';
import { isNumber, Translate, translate, ValidatedField, ValidatedForm } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IDefType } from 'app/shared/model/def-type.model';
import { getEntities as getDefTypes } from 'app/entities/def-type/def-type.reducer';
import { getEntities as getDefItems } from 'app/entities/def-item/def-item.reducer';
import { getEntity, updateEntity, createEntity, reset } from './def-item.reducer';
import { IDefItem } from 'app/shared/model/def-item.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

export const DefItemUpdate = (props: RouteComponentProps<{ id: string }>) => {
  const dispatch = useAppDispatch();

  const [isNew] = useState(!props.match.params || !props.match.params.id);

  const defTypes = useAppSelector(state => state.defType.entities);
  const defItems = useAppSelector(state => state.defItem.entities);
  const defItemEntity = useAppSelector(state => state.defItem.entity);
  const loading = useAppSelector(state => state.defItem.loading);
  const updating = useAppSelector(state => state.defItem.updating);
  const updateSuccess = useAppSelector(state => state.defItem.updateSuccess);
  const handleClose = () => {
    props.history.push('/def-item');
  };

  useEffect(() => {
    if (isNew) {
      dispatch(reset());
    } else {
      dispatch(getEntity(props.match.params.id));
    }

    dispatch(getDefTypes({}));
    dispatch(getDefItems({}));
  }, []);

  useEffect(() => {
    if (updateSuccess) {
      handleClose();
    }
  }, [updateSuccess]);

  const saveEntity = values => {
    const entity = {
      ...defItemEntity,
      ...values,
      type: defTypes.find(it => it.id.toString() === values.type.toString()),
      parent: defItems.find(it => it.id.toString() === values.parent.toString()),
    };

    if (isNew) {
      dispatch(createEntity(entity));
    } else {
      dispatch(updateEntity(entity));
    }
  };

  const defaultValues = () =>
    isNew
      ? {}
      : {
          ...defItemEntity,
          type: defItemEntity?.type?.id,
          parent: defItemEntity?.parent?.id,
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="startpointApp.defItem.home.createOrEditLabel" data-cy="DefItemCreateUpdateHeading">
            <Translate contentKey="startpointApp.defItem.home.createOrEditLabel">Create or edit a DefItem</Translate>
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <ValidatedForm defaultValues={defaultValues()} onSubmit={saveEntity}>
              {!isNew ? (
                <ValidatedField
                  name="id"
                  required
                  readOnly
                  id="def-item-id"
                  label={translate('startpointApp.defItem.id')}
                  validate={{ required: true }}
                />
              ) : null}
              <ValidatedField
                label={translate('startpointApp.defItem.code')}
                id="def-item-code"
                name="code"
                data-cy="code"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                  maxLength: { value: 20, message: translate('entity.validation.maxlength', { max: 20 }) },
                }}
              />
              <ValidatedField
                label={translate('startpointApp.defItem.name')}
                id="def-item-name"
                name="name"
                data-cy="name"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                  maxLength: { value: 200, message: translate('entity.validation.maxlength', { max: 200 }) },
                }}
              />
              <ValidatedField
                id="def-item-type"
                name="type"
                data-cy="type"
                label={translate('startpointApp.defItem.type')}
                type="select"
                required
              >
                <option value="" key="0" />
                {defTypes
                  ? defTypes.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.id}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <FormText>
                <Translate contentKey="entity.validation.required">This field is required.</Translate>
              </FormText>
              <ValidatedField
                id="def-item-parent"
                name="parent"
                data-cy="parent"
                label={translate('startpointApp.defItem.parent')}
                type="select"
              >
                <option value="" key="0" />
                {defItems
                  ? defItems.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.code}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/def-item" replace color="info">
                <FontAwesomeIcon icon="arrow-left" />
                &nbsp;
                <span className="d-none d-md-inline">
                  <Translate contentKey="entity.action.back">Back</Translate>
                </span>
              </Button>
              &nbsp;
              <Button color="primary" id="save-entity" data-cy="entityCreateSaveButton" type="submit" disabled={updating}>
                <FontAwesomeIcon icon="save" />
                &nbsp;
                <Translate contentKey="entity.action.save">Save</Translate>
              </Button>
            </ValidatedForm>
          )}
        </Col>
      </Row>
    </div>
  );
};

export default DefItemUpdate;
