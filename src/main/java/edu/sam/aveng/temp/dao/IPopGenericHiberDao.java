package edu.sam.aveng.temp.dao;

import edu.sam.aveng.base.contract.v1.dao.IGenericDao;
import edu.sam.aveng.temp.util.Populatable;

import java.io.Serializable;

public interface IPopGenericHiberDao<T extends Serializable> extends IGenericDao<T>, Populatable {
}
