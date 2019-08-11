package edu.sam.aveng.temp.dao;

import edu.sam.aveng.base.contract.dao.IGenericDao;
import edu.sam.aveng.base.util.Populatable;

import java.io.Serializable;

public interface IPopGenericHiberDao<T extends Serializable> extends IGenericDao<T>, Populatable {
}
