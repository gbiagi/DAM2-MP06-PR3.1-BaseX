declare option output:method "xml";
declare option output:indent "yes";

<TopTags>
{
    let $tags :=
        for $tag in doc("anime/Tags.xml")//row
        let $tagName := $tag/@TagName/string()
        let $count := $tag/@Count/string()
        order by xs:integer($count) descending
        return <Tag>
                   <Name>{$tagName}</Name>
                   <Uses>{$count}</Uses>
               </Tag>

    return subsequence($tags, 1, 10)
}
</TopTags>
