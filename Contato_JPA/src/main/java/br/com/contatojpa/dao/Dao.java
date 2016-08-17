/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.contatojpa.dao;

import br.com.contatojpa.model.Model;
import java.util.List;

/**
 *
 * @author Joel Rodrigues
 */
public interface Dao<T extends Model> {

    public T getById(Long id) throws Exception;

    public T save(T t) throws Exception;

    public boolean delete(T t) throws Exception;

    public List<T> list() throws Exception;

    public Long count() throws Exception;
}
