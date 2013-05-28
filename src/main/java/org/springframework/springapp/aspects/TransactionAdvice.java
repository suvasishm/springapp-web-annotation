package org.springframework.springapp.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.orm.hibernate4.HibernateTransactionManager;

@Aspect
public class TransactionAdvice {
	
	private HibernateTransactionManager txManager;
	private SessionFactory sessionFactory;
	
	/** Logger for this class and subclasses */
    protected final Logger logger = LoggerFactory.getLogger(getClass());
    
    public void setTxManagerFactory(HibernateTransactionManager txManager) {
		this.txManager = txManager;		
		this.sessionFactory = this.txManager.getSessionFactory();
	}

	@Pointcut("execution(* org.springframework.springapp.dao.*.*(..))")
	public void dataAccessOperation() {
	}

	/*@Before("dataAccessOperation()")
	public void startTransaction() {
		System.out.println("starting....");
	}
	
	@After("dataAccessOperation()")
	public void endTransaction() {
		System.out.println("ending....");
	}*/
	
	@Around("dataAccessOperation()")
	public Object atTransaction(ProceedingJoinPoint pjp) throws Throwable {
		Session session = this.sessionFactory.getCurrentSession();
        session.beginTransaction();
        
        pjp.proceed();
        
        session.getTransaction().commit();
		return 0;
	}
	
}
