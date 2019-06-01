package generate;

/**
 * ServiceImplGenerater
 * @author wangsihong@hztianque.com
 * @date 2018��10��26�� ����1:44:21 
 *
 */
public class ServiceImplGenerater extends AbstractGenerater{

  public ServiceImplGenerater(String classPath, String domainName, String author) {
    setAuthor(author);
    setDoMainName(domainName);
    setClassPath(classPath);
  }
  
  @Override
  public String building() {
    StringBuilder sb = new StringBuilder();
    sb.append("package "+getClassPath()+"."+getService()+".impl;\n\n");
    sb.append("import java.util.List;\n\n");
    sb.append("import org.springframework.beans.factory.annotation.Autowired;\n");
    sb.append("import org.springframework.stereotype.Service;\n\n");
    sb.append("import com.github.pagehelper.PageHelper;\n");
    sb.append("import com.github.pagehelper.PageInfo;\n");
    sb.append("import com.tianque.core.base.util.StringUtil;\n");
    sb.append("import com.tianque.exception.base.BusinessValidationException;\n");
    sb.append("import com.tianque.exception.base.ServiceValidationException;\n");
    sb.append("import "+getClassPath()+"."+getDomain()+"."+getUpperCaseDomainName()+";\n");
    sb.append("import "+getClassPath()+"."+getMapper()+"."+getUpperCaseDomainName()+"Mapper;\n");
    sb.append("import "+getClassPath()+"."+getService()+"."+getUpperCaseDomainName()+"Service;\n");
    sb.append("import "+getClassPath()+"."+getVo()+"."+getUpperCaseDomainName()+"VO;\n\n");
    sb.append("/**\n");
    sb.append(" * "+getUpperCaseDomainName()+"ServiceImpl\n");
    sb.append(" * @author "+getAuthor()+"\n");
    sb.append(" * @date "+getDate()+"\n");
    sb.append(" */\n");
    sb.append("@Service(\""+getLowerCaseDomainName()+"Service\")\n");
    sb.append("public class "+getUpperCaseDomainName()+"ServiceImpl implements "+getUpperCaseDomainName()+"Service {\n\n");
    
    sb.append("\t@Autowired\n");
    sb.append("\tprivate "+getUpperCaseDomainName()+"Mapper "+getLowerCaseDomainName()+"Mapper;\n\n");
    
    //add
    sb.append("\t@Override\n");
    sb.append("\tpublic "+getUpperCaseDomainName()+" add"+getUpperCaseDomainName()+"("+getUpperCaseDomainName()+" "+getLowerCaseDomainName()+") {\n");
    sb.append("\t\tif ("+getLowerCaseDomainName()+" == null) {\n");
    sb.append("\t\t\tthrow new BusinessValidationException(\""+getLowerCaseDomainName()+"����Ϊ��\");\n");
    sb.append("\t\t}\n");
    sb.append("\t\ttry {\n");
    sb.append("\t\t\t"+getLowerCaseDomainName()+"Mapper.add"+getUpperCaseDomainName()+"("+getLowerCaseDomainName()+");\n");
    sb.append("\t\t\treturn "+getLowerCaseDomainName()+";\n");
    sb.append("\t\t} catch (Exception e) {\n");
    sb.append("\t\t\tthrow new ServiceValidationException(\"����"+getLowerCaseDomainName()+"����\"+"+getLowerCaseDomainName()+", e);\n");
    sb.append("\t\t}\n");
    sb.append("\t}\n\n");
    
    //update
    sb.append("\t@Override\n");
    sb.append("\tpublic "+getUpperCaseDomainName()+" update"+getUpperCaseDomainName()+"("+getUpperCaseDomainName()+" "+getLowerCaseDomainName()+") {\n");
    sb.append("\t\tif ("+getLowerCaseDomainName()+" == null || "+getLowerCaseDomainName()+".getId() == null) {\n");
    sb.append("\t\t\tthrow new BusinessValidationException(\"����"+getLowerCaseDomainName()+"����Ϊ��\");\n");
    sb.append("\t\t}\n");
    sb.append("\t\ttry {\n");
    sb.append("\t\t\t"+getLowerCaseDomainName()+"Mapper.update"+getUpperCaseDomainName()+"("+getLowerCaseDomainName()+");\n");
    sb.append("\t\t\treturn "+getLowerCaseDomainName()+";\n");
    sb.append("\t\t} catch (Exception e) {\n");
    sb.append("\t\t\tthrow new ServiceValidationException(\"����"+getLowerCaseDomainName()+"����\"+"+getLowerCaseDomainName()+", e);\n");
    sb.append("\t\t}\n");
    sb.append("\t}\n\n");
    
    //deleteByIds
    sb.append("\t@Override\n");
    sb.append("\tpublic Boolean delete"+getUpperCaseDomainName()+"ByIds(Long[] ids) {\n");
    sb.append("\t\tif (null != ids && ids.length == 0) {\n");
    sb.append("\t\t\tthrow new BusinessValidationException(\"ɾ��"+getLowerCaseDomainName()+"��������\");\n");
    sb.append("\t\t}\n");
    sb.append("\t\ttry {\n");
    sb.append("\t\t\tLong count = "+getLowerCaseDomainName()+"Mapper.delete"+getUpperCaseDomainName()+"ByIds(ids);\n");
    sb.append("\t\t\tif (count > 0) {\n");
    sb.append("\t\t\t\treturn true;\n");
    sb.append("\t\t\t} else {\n");
    sb.append("\t\t\t\treturn false;\n");
    sb.append("\t\t\t}\n");
    sb.append("\t\t} catch (Exception e) {\n");
    sb.append("\t\t\tthrow new ServiceValidationException(\"ɾ��"+getLowerCaseDomainName()+"����\", e);\n");
    sb.append("\t\t}\n");
    sb.append("\t}\n\n");
    
    //getById
    sb.append("\t@Override\n");
    sb.append("\tpublic "+getUpperCaseDomainName()+" get"+getUpperCaseDomainName()+"ById(Long id) {\n");
    sb.append("\t\tif (null == id) {\n");
    sb.append("\t\t\tthrow new BusinessValidationException(\"��ѯ"+getLowerCaseDomainName()+"��������\");\n");
    sb.append("\t\t}\n");
    sb.append("\t\ttry {\n");
    sb.append("\t\t\treturn "+getLowerCaseDomainName()+"Mapper.get"+getUpperCaseDomainName()+"ById(id);\n");
    sb.append("\t\t} catch (Exception e) {\n");
    sb.append("\t\t\tthrow new ServiceValidationException(\"��ѯ"+getLowerCaseDomainName()+"����\", e);\n");
    sb.append("\t\t}\n");
    sb.append("\t}\n\n");
    
    //findForPageHelper
    sb.append("\t@Override\n");
    sb.append("\tpublic PageInfo<"+getUpperCaseDomainName()+"> find"+getUpperCaseDomainName()+"ForPageHelper(\n");
    sb.append("\t\t\t"+getUpperCaseDomainName()+"VO "+getLowerCaseDomainName()+"VO) {\n");
    sb.append("\t\tif (null == "+getLowerCaseDomainName()+"VO || null == "+getLowerCaseDomainName()+"VO.get"+getUpperCaseDomainName()+"()) {\n");
    sb.append("\t\t\tthrow new BusinessValidationException(\"��ѯ"+getLowerCaseDomainName()+"�б��������\");\n");
    sb.append("\t\t}\n");
    sb.append("\t\tPageHelper.startPage("+getLowerCaseDomainName()+"VO.getPage(),"+getLowerCaseDomainName()+"VO.getRows(), \n");
    sb.append("\t\t\t\tStringUtil.joinSortFieldOrder("+getLowerCaseDomainName()+"VO.getSidx(),"+getLowerCaseDomainName()+"VO.getSord()));\n");
    sb.append("\t\ttry {\n");
    sb.append("\t\t\tList<"+getUpperCaseDomainName()+"> list = "+getLowerCaseDomainName()+"Mapper.find"+getUpperCaseDomainName()+"ForList("+getLowerCaseDomainName()+"VO.get"+getUpperCaseDomainName()+"());\n");
    sb.append("\t\t\treturn new PageInfo<"+getUpperCaseDomainName()+">(list);\n");
    sb.append("\t\t} catch (Exception e) {\n");
    sb.append("\t\t\tthrow new ServiceValidationException(\"��ѯ"+getLowerCaseDomainName()+"����\"+"+getLowerCaseDomainName()+"VO, e);\n");
    sb.append("\t\t}\n");
    sb.append("\t}\n\n");
    
    
    sb.append("}\n");
    return sb.toString();
  }

}
